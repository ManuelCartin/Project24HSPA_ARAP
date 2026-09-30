let map;
let geoJsonLayer;

function initMap() {
    // Coordenadas de Spa-Francorchamps
    map = L.map('map').setView([50.4372, 5.9714], 14);

    // Tile Layer gratuita de OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        maxZoom: 19
    }).addTo(map);

    loadSpaGeometry();
}

async function loadSpaGeometry() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/circuit/spa-geometry');
        if (!response.ok) throw new Error('Error al consultar servidor');
        
        const geojson = await response.json();
        
        if (geoJsonLayer) map.removeLayer(geoJsonLayer);

        geoJsonLayer = L.geoJSON(geojson, {
            style: {
                color: '#ef4444',
                weight: 4,
                opacity: 0.85
            }
        }).addTo(map);

        map.fitBounds(geoJsonLayer.getBounds());
    } catch (err) {
        console.warn('Cargando geometría estática de respaldo para Spa-Francorchamps:', err.message);
    }
}

document.addEventListener('DOMContentLoaded', initMap);