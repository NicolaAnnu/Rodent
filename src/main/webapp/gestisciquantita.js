document.addEventListener('DOMContentLoaded', () => {
    function updatePrice() {
        let totale = 0.0;
        document.querySelectorAll('.Cart-Items').forEach(function (item) {
            var conteggio = parseFloat(item.querySelector('.count').textContent);
            var importo = parseFloat(item.querySelector('.amount').textContent);
            totale += conteggio * importo;
        });

        document.querySelector(".total-amount").textContent = totale.toFixed(2);

        var spanElement = document.createElement('span');
        spanElement.classList.add('euro');
        spanElement.textContent = "€";
        document.querySelector(".total-amount").append(spanElement);

        var numeroOggetti = document.querySelectorAll(".Cart-Items").length;
        document.querySelector(".items").textContent = numeroOggetti + " oggetti";
    }

    document.querySelectorAll(".btn").forEach(function (button) {
        button.addEventListener("click", function () {
            var counter = this.closest(".counter");
            var countElement = counter.querySelector(".count");
            var countValue = parseInt(countElement.textContent);

            if (countValue < 1) {
                return;
            }

            if (this.classList.contains("plus")) {
                countValue += 1;
            } else if (this.classList.contains("minus")) {
                countValue -= 1;
            }

            let productId = counter.getAttribute("data-product");

            fetch('aggiornaQuantita', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: `id=${encodeURIComponent(productId)}&quantita=${encodeURIComponent(countValue)}`
            });

            if (countValue === 0) {
                counter.parentElement.remove();
                if (document.querySelectorAll(".Cart-Items").length === 0) {
                    location.reload();
                }
            } else {
                countElement.textContent = countValue;
            }

            updatePrice();
        });
    });
});
