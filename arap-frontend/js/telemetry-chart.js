let telemetryChart;

function initChart() {
    const ctx = document.getElementById('telemetryChart').getContext('2d');
    
    telemetryChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['00:00', '02:00', '04:00', '06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00'],
            datasets: [{
                label: 'Velocidad (km/h)',
                data: [120, 245, 180, 270, 95, 260, 280, 110, 255, 272, 140],
                borderColor: '#ef4444',
                backgroundColor: 'rgba(239, 68, 68, 0.1)',
                borderWidth: 2,
                fill: true,
                tension: 0.3,
                pointRadius: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: { grid: { color: '#1e293b' }, ticks: { color: '#64748b', font: { size: 10 } } },
                y: { grid: { color: '#1e293b' }, ticks: { color: '#64748b', font: { size: 10 } } }
            },
            plugins: {
                legend: { display: false }
            }
        }
    });
}

async function fetchTelemetryData() {
    try {
        const response = await fetch('http://localhost:8080/api/v1/telemetry/gt3/SESSION_SPA_01');
        if (!response.ok) throw new Error('No se encontraron datos de telemetría');
        
        const data = await response.json();
        
        telemetryChart.data.labels = data.map(d => new Date(d.timestamp).toLocaleTimeString());
        telemetryChart.data.datasets[0].data = data.map(d => d.speedKmh);
        telemetryChart.update();
    } catch (err) {
        console.warn('API en espera. Ejecutando datos de simulación:', err.message);
    }
}

document.addEventListener('DOMContentLoaded', initChart);