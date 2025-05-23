document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".menu-options li a").forEach(link => {
        link.addEventListener("click", function () {
            document.querySelectorAll(".menu-options li").forEach(li => {
                li.classList.remove("selected");
            });

            this.closest("li").classList.add("selected");

            document.querySelectorAll(".menu-item").forEach(item => {
                item.style.display = "none";
            });

            let target = this.closest("li").textContent.replace(" ", "-");
            let targetElement = document.querySelector(`#${target}.menu-item`);
            if (targetElement) {
                targetElement.style.display = "block";
            }

            if (target.toLowerCase() === "preferiti") {
                fetch("preferiti.jsp")
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector("#preferiti").innerHTML = html;
                    });
            }

            if (target.toLowerCase() === "ordini") {
                fetch("ordini.jsp")
                    .then(response => response.text())
                    .then(html => {
                        document.querySelector("#ordini").innerHTML = html;
                    });
            }
        });
    });
});
