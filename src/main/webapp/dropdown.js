document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".dropdown-button").forEach(button => {
        button.addEventListener("click", function () {
            document.querySelectorAll(".small-navigation").forEach(nav => {
                if (getComputedStyle(nav).display === "none") {
                    nav.style.display = "block";
                    nav.style.transition = "all 0.3s ease"; // Simulazione tempo 300ms
                } else {
                    nav.style.display = "none";
                }
            });
        });
    });
});
