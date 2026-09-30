let map;
let geoJsonLayer;

function initMap() {
    // Coordenadas iniciales centradas en Spa-Francorchamps
    map = L.map('map').setView([50.4372, 5.9714], 14);

    // Servidor de mapas satelitales sin API Key obligatoria
    L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
        attribution: 'Tiles &copy; Esri &mdash; Source: Esri, i-cubed, USDA, USGS, AEX, GeoEye, Getmapping, Aerogrid, IGN, IGP, UPR-EGP, and the GIS User Community',
        maxZoom: 18
    }).addTo(map);

    loadSpaGeometry();
}

async function loadSpaGeometry() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/circuit/spa-geometry');
        if (!response.ok) throw new Error('Error al consultar servidor Spring Boot');
        
        const geojson = await response.json();
        
        if (geoJsonLayer) map.removeLayer(geoJsonLayer);

        geoJsonLayer = L.geoJSON(geojson, {
            // 1. Estilos dinámicos para los sectores (LineString)
            style: (feature) => {
                const sectorName = feature.properties?.name;
                switch (sectorName) {
                    case 'Sector 1':
                        return { color: '#ef4444', weight: 4, opacity: 0.9 }; // Red GT3
                    case 'Sector 2':
                        return { color: '#10b981', weight: 4, opacity: 0.9 }; // Emerald
                    case 'Sector 3':
                        return { color: '#3b82f6', weight: 4, opacity: 0.9 }; // Blue
                    default:
                        return { color: '#f59e0b', weight: 3, opacity: 0.8 }; // Amber
                }
            },

            // 2. Renderizado de curvas e hitos clave (Point)
            pointToLayer: (feature, latlng) => {
                return L.circleMarker(latlng, {
                    radius: 4,
                    fillColor: '#f59e0b', // Ámbar
                    color: '#020617',     // Slate-950
                    weight: 1.5,
                    opacity: 1,
                    fillOpacity: 0.9
                });
            },

            // 3. Popups interactivos estilizados
            onEachFeature: (feature, layer) => {
                if (feature.properties && feature.properties.name) {
                    const altitude = feature.geometry.coordinates && feature.geometry.coordinates[2] !== undefined 
                        ? `<br><span class="text-xs text-slate-400 font-mono">Altitud: ${feature.geometry.coordinates[2]} m</span>` 
                        : '';
                    
                    layer.bindPopup(`
                        <div class="font-sans text-slate-100 p-1">
                            <strong class="text-sm font-bold text-slate-200">${feature.properties.name}</strong>
                            ${altitude}
                        </div>
                    `);
                }
            }
        }).addTo(map);

        // Ajustar el zoom automáticamente al encuadre del circuito
        map.fitBounds(geoJsonLayer.getBounds(), { padding: [15, 15] });

    } catch (err) {
        console.warn('Cargando geometría estática de respaldo para Spa-Francorchamps:', err.message);
    }
}

document.addEventListener('DOMContentLoaded', initMap);