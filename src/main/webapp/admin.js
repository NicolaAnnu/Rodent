window.addEventListener("DOMContentLoaded", () => {
    const aggiornaButtons = document.querySelectorAll('.Aggiorna');

    aggiornaButtons.forEach(button => {
        button.addEventListener("click", function () {
            const prezzoInput = this.parentElement.querySelector('input[name="prezzo"]');
            const quantitaSelect = this.parentElement.querySelector('select');
            const productLine = this.closest('.product-line');

            const prezzo = prezzoInput ? prezzoInput.value : "";
            const quantita = quantitaSelect ? quantitaSelect.value : "";
            const productId = productLine ? productLine.id.replace("prodotto-", "") : "";

            console.log('Prezzo:', prezzo);
            console.log('Quantità:', quantita);
            console.log('ID Prodotto:', productId);

            const formData = new URLSearchParams();
            formData.append("id", productId);
            formData.append("prezzo", prezzo);
            formData.append("quantita", quantita);

            fetch("aggiornaProdotto", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData.toString()
            });
        });
    });
});
