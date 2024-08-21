document.addEventListener('DOMContentLoaded', () => {
    const productBuyButtons = document.querySelectorAll('.product-buy-button');

    productBuyButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Ottieni l'id del prodotto dal data-attribute
            let id = button.getAttribute('data-product');

            // Ottieni il valore della quantità
            let quantita = document.getElementById('quantita').value;

            // Aggiungi la classe 'clicked' al pulsante
            button.classList.add('clicked');

            // Rimuovi la classe 'clicked' dopo 300 ms
            setTimeout(() => {
                button.classList.remove('clicked');
            }, 300);
            // Esegui una richiesta AJAX con Fetch API
            fetch('addToCart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `id=${encodeURIComponent(id)}&quantita=${encodeURIComponent(quantita)}`
            })
                .then(response => {
                    if (response.status === 409) {
                        alert("Prodotto non aggiunto, disponibilità esaurita");
                    }
                })
                .catch(error => {
                    console.error('Errore:', error);
                });
        });
    });
});
