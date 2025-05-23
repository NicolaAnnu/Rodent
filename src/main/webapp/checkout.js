document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#checkout-form").addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new URLSearchParams(new FormData(this)).toString();

        fetch("checkout", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        }).then(response => {
            if (response.ok) {
                window.location.href = "index.jsp";
            } else {
                return response.text().then(error => {
                    alert(JSON.stringify(error));
                    alert("Alcuni prodotti non erano disponibili, la pagina verrà ricaricata");
                    window.location.reload();
                });
            }
        });
    });
});
