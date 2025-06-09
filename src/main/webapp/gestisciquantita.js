document.addEventListener("DOMContentLoaded", () => {

    function updatePrice() {
        let totale = 0.0;

        document.querySelectorAll(".Cart-Items").forEach(item => {
            const count = parseFloat(item.querySelector(".count").textContent);
            const amount = parseFloat(item.querySelector(".amount").textContent);
            totale += count * amount;
        });

        const totalAmountElem = document.querySelector(".total-amount");
        totalAmountElem.textContent = totale.toFixed(2);

        const spanElement = document.createElement("span");
        spanElement.classList.add("euro");
        spanElement.textContent = "€";
        totalAmountElem.appendChild(spanElement);

        const numeroOggetti = document.querySelectorAll(".Cart-Items").length;
        const itemsElem = document.querySelector(".items");
        itemsElem.textContent = numeroOggetti + " oggetti";
    }

    document.querySelectorAll(".btn").forEach(btn => {
        btn.addEventListener("click", function () {
            const counter = this.closest(".counter");
            const countElement = counter.querySelector(".count");
            let countValue = parseInt(countElement.textContent);

            if (countValue < 1) return;

            if (this.classList.contains("plus")) {
                countValue += 1;
            } else if (this.classList.contains("minus")) {
                countValue -= 1;
            }

            const productId = counter.dataset.product;

            // Invia POST in stile jQuery
            fetch("/annunziata_war/aggiornaQuantita", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `id=${encodeURIComponent(productId)}&quantita=${encodeURIComponent(countValue)}`
            });

            if (countValue === 0) {
                counter.parentElement.remove();
                if (document.querySelectorAll(".Cart-Items").length === 0) {
                    location.reload();
                    return;
                }
            } else {
                countElement.textContent = countValue;
            }

            updatePrice();
        });
    });

});
