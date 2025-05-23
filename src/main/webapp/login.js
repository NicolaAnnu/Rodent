document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#login-form").addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new URLSearchParams(new FormData(this)).toString();

        fetch("login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        }).then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                const errorBox = document.querySelector(".messaggio-errore");
                if (errorBox) {
                    errorBox.style.transition = "max-height 0.4s ease";
                    errorBox.style.overflow = "hidden";
                    errorBox.style.maxHeight = errorBox.scrollHeight + "px";

                    setTimeout(() => {
                        errorBox.style.maxHeight = "0";
                    }, 2000);
                }
            }
        });
    });
});
