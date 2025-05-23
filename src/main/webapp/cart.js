document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".product-buy-button").forEach(button => {
        button.addEventListener("click", function () {
            let id = this.dataset.product;
            let quantita = document.querySelector("#quantita").value;

            this.classList.add("clicked");

            setTimeout(() => {
                this.classList.remove("clicked");
            }, 300);

            const formData = new URLSearchParams();
            formData.append("id", id);
            formData.append("quantita", quantita);

            fetch("addToCart", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData.toString()
            }).then(response => {
                if (response.status === 409) {
                    alert("Prodotto non aggiunto, disponibilità esaurita");
                }
            });
        });
    });
});
