document.addEventListener('DOMContentLoaded', function () {
    const checkoutForm = document.getElementById('checkout-form');

    checkoutForm.addEventListener('submit', function (event) {
        event.preventDefault();

        // Serializzazione del form
        const formData = new URLSearchParams(new FormData(checkoutForm)).toString();

        // Richiesta con Fetch API
        fetch('checkout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = 'index.jsp';
                } else {
                    throw new Error('Alcuni prodotti non erano disponibili');
                }
            })
            .catch(error => {
                alert(JSON.stringify(error));
                alert('Alcuni prodotti non erano disponibili, la pagina verrà ricaricata');
                window.location.reload();
            });
    });
});
