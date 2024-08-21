document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.Aggiorna').forEach(function (button) {
        button.addEventListener('click', function () {
            var prezzo = this.parentElement.querySelector('input[name="prezzo"]').value;
            var quantita = this.parentElement.querySelector('select').value;
            var productId = this.closest('.product-line').id.replace("prodotto-", "");

            console.log('Prezzo:', prezzo);
            console.log('Quantità:', quantita);
            console.log('ID Prodotto:', productId);

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "aggiornaProdotto", true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.send("id=" + encodeURIComponent(productId) + "&prezzo=" + encodeURIComponent(prezzo) + "&quantita=" + encodeURIComponent(quantita));
        });
    });
});
