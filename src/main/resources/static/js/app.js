document.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById('healthCheckBtn');
    const result = document.getElementById('statusResult');

    btn.addEventListener('click', async () => {
        try {
            // This expects the backend to be running!
            const response = await fetch('/api/health');
            const data = await response.json();
            result.textContent = JSON.stringify(data, null, 2);
        } catch (error) {
            result.textContent = "Error: Backend is not reachable.";
        }
    });
});
