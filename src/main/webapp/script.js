function handleClickPreferiti() {
    document.querySelectorAll(".heart-button").forEach(button => {
        button.addEventListener("click", function () {
            let id = this.closest("div.product-card")?.id;
            if (id === undefined) {
                id = (new URL(window.location.href)).searchParams.get("id");
            }
            let images = this.querySelectorAll("img");

            fetch("addPreferiti?id=" + id)
                .then(response => {
                    if (response.ok) {
                        images.forEach(img => {
                            img.style.display = (img.style.display === "none" || getComputedStyle(img).display === "none") ? "inline" : "none";
                        });
                    } else if (response.status === 403) {
                        alert("Devi essere registrato per poter aggiungere cose ai preferiti");
                    }
                });
        });
    });
}


function handlePreferiti() {
    fetch("getPreferiti")
        .then(response => response.json())
        .then(data => {
            for (let i = 0; i < data.length; i++) {
                let divEsterno = document.querySelector("div#" + data[i] + ".product-card");
                let button = divEsterno.querySelector("button");
                let images = button.querySelectorAll("img");
                images.forEach(img => {
                    img.style.display = (img.style.display === "none" || getComputedStyle(img).display === "none") ? "inline" : "none";
                });
            }
        });
}


function handleSearch() {
    document.querySelectorAll(".search-button").forEach(button => {
        button.addEventListener("click", function search() {
            let searchParameter = document.querySelector(".search-container input").value;

            fetch("cercaProdotti?searchParam=" + encodeURIComponent(searchParameter))
                .then(response => response.json())
                .then(data => {
                    let container = document.querySelector(".card-container");
                    container.innerHTML = "";

                    let active = document.querySelector(".active");
                    if (active) {
                        active.classList.remove("active");
                    }

                    data.forEach(function (item) {
                        let card = document.createElement("div");
                        card.classList.add("product-card");
                        card.id = item.id;

                        let link = document.createElement("a");
                        link.href = "visualizzaProdotto.jsp?id=" + item.id;

                        let image = document.createElement("img");
                        image.src = "./images/" + item.id + "/1.jpg";

                        let productName = document.createElement("h4");
                        productName.textContent = item.nome;

                        link.appendChild(image);
                        link.appendChild(productName);

                        let internalDiv = document.createElement("div");
                        internalDiv.classList.add("internal-div");

                        let price = document.createElement("span");
                        price.textContent = item.prezzo + "€";

                        let heartButton = document.createElement("button");
                        heartButton.classList.add("heart-button");

                        let heartIcon = document.createElement("img");
                        heartIcon.src = "icons/heartIcon.svg";

                        let clickedHeartIcon = document.createElement("img");
                        clickedHeartIcon.src = "icons/heartIconClicked.svg";
                        clickedHeartIcon.style.display = "none";

                        heartButton.appendChild(heartIcon);
                        heartButton.appendChild(clickedHeartIcon);

                        internalDiv.appendChild(price);
                        internalDiv.appendChild(heartButton);

                        card.appendChild(link);
                        card.appendChild(internalDiv);

                        container.appendChild(card);
                    });

                    handlePreferiti();
                    handleClickPreferiti();
                });
        });
    });
}

document.addEventListener("DOMContentLoaded", function () {
    handlePreferiti();
    handleClickPreferiti();
    handleSearch();
});


