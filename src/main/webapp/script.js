function handleClickPreferiti() {
    document.querySelectorAll(".heart-button").forEach(button => {
        button.addEventListener("click", function () {
            let productCard = this.closest("div.product-card");
            let id = productCard ? productCard.id : new URL(window.location.href).searchParams.get("id");

            if (id === undefined) {
                return;
            }

            let images = this.querySelectorAll("img");

            fetch(`addPreferiti?id=${id}`)
                .then(response => {
                    if (response.ok) {
                        images.forEach(image => {
                            image.style.display = image.style.display === 'none' ? 'block' : 'none';
                        });
                    } else {
                        throw response;
                    }
                })
                .catch(error => {
                    if (error.status === 403) {
                        alert("Devi essere registrato per poter aggiungere un prodotto ai preferiti");
                    }
                });
        });
    });
}

function handlePreferiti() {
    fetch("getPreferiti")
        .then(response => response.json())
        .then(data => {
            data.forEach(item => {
                let divEsterno = document.querySelector(`div#${item}.product-card`);
                if (divEsterno) {
                    let heartIcons = divEsterno.querySelectorAll("button img");
                    heartIcons.forEach(icon => {
                        icon.style.display = icon.style.display === 'none' ? 'block' : 'none';
                    });
                }
            });
        });
}

function handleSearch() {
    document.querySelector(".search-button").addEventListener("click", function search() {
        let searchParameter = document.querySelector(".search-container input").value;

        fetch(`cercaProdotti?searchParam=${searchParameter}`)
            .then(response => response.json())
            .then(data => {
                let container = document.querySelector(".card-container");
                container.innerHTML = '';  // Rimuove tutti i figli

                document.querySelector(".active").classList.toggle("active");

                data.forEach(item => {
                    let card = document.createElement('div');
                    card.classList.add('product-card');
                    card.id = item.id;

                    let link = document.createElement('a');
                    link.href = `visualizzaProdotto.jsp?id=${item.id}`;

                    let image = document.createElement('img');
                    image.src = `./images/${item.id}/1.jpg`;

                    let productName = document.createElement('h4');
                    productName.textContent = item.nome;

                    link.append(image, productName);

                    let internalDiv = document.createElement('div');
                    internalDiv.classList.add('internal-div');

                    let price = document.createElement('span');
                    price.textContent = `${item.prezzo}€`;

                    let heartButton = document.createElement('button');
                    heartButton.classList.add('heart-button');

                    let heartIcon = document.createElement('img');
                    heartIcon.src = 'icons/heartIcon.svg';

                    let clickedHeartIcon = document.createElement('img');
                    clickedHeartIcon.src = 'icons/heartIconClicked.svg';
                    clickedHeartIcon.style.display = 'none';

                    heartButton.append(heartIcon, clickedHeartIcon);
                    internalDiv.append(price, heartButton);
                    card.append(link, internalDiv);
                    container.append(card);
                });

                handlePreferiti();
                handleClickPreferiti();
            });
    });
}

document.addEventListener('DOMContentLoaded', function () {
    handlePreferiti();
    handleClickPreferiti();
    handleSearch();
});
