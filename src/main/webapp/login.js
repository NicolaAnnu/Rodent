document.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();

        // Serializza il form
        const formData = new URLSearchParams(new FormData(loginForm)).toString();

        // Effettua la richiesta AJAX
        fetch('login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    window.location.reload();
                } else {
                    throw new Error('Login failed');
                }
            })
            .catch(error => {
                const errorMessage = document.querySelector('.messaggio-errore');
                // Mostra il messaggio di errore con un effetto toggle
                errorMessage.style.display = 'block';
                setTimeout(() => {
                    errorMessage.style.display = 'none';
                }, 2000);
            });
    });
});
