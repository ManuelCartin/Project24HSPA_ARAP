function login(event) {
    if (event) event.preventDefault();
    localStorage.setItem('arap_session', JSON.stringify({
        user: 'Ingeniero Telemetría',
        role: 'Audi Sport GT3 Engineer',
        timestamp: new Date().toISOString()
    }));
    window.location.href = 'dashboard.html';
}

function checkAuth() {
    const session = localStorage.getItem('arap_session');
    if (!session && !window.location.pathname.endsWith('index.html')) {
        window.location.href = 'index.html';
    }
}

function logout() {
    localStorage.removeItem('arap_session');
    window.location.href = 'index.html';
}