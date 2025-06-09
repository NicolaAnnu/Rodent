document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".aggiorna-ordine-btn").forEach(button => {
        button.addEventListener("click", () => {
            const riga = button.closest(".riga");
            const status = riga.querySelector("select").value;
            const idOrdine = riga.querySelector(".id-ordine").textContent.trim();

            console.log("status:", status);
            console.log("idOrdine:", idOrdine);

            fetch("/annunziata_war/gestioneOrdini", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `id=${encodeURIComponent(idOrdine)}&status=${encodeURIComponent(status)}`
            });
        });
    });
});
