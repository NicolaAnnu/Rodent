function handleClickPreferiti() {
    $(".heart-button").click(function () {
        let id = $(this).parents("div.product-card").attr("id");
        if (id === undefined) {
            id = (new URL(window.location.href)).searchParams.get("id");
        }
        let images = $(this).children("img");
        $.ajax({
            url: "addPreferiti?id=" + id, success: (data) => {
                images.toggle()
            },
            error: (xhr, status, error) => {
                if (xhr.status === 403) {
                    alert("Devi essere registrato per poter aggiungere cose ai preferiti")
                }
            }
        })
    })
}


function handlePreferiti() {
    $.getJSON("getPreferiti", (data) => {
        for (let i = 0; i < data.length; i++) {
            let divEsterno = $("div#" + data[i] + ".product-card");
            divEsterno.find("button").children("img").toggle()
        }
    });
}

function handleSearch() {
    $(".search-button").click(
        function search() {
            let searchParameter = $(".search-container input").val();
            $.getJSON("cercaProdotti?searchParam=" + searchParameter,
                (data) => {
                    let container = $(".card-container");
                    container.children().remove();
                    $(".active").toggleClass("active");
                    var html = '';
                    data.forEach(function (item) {
                        var card = $('<div>').addClass('product-card').attr('id', +item.id);
                        var link = $('<a>').attr('href', 'visualizzaProdotto.jsp?id=' + item.id);
                        var image = $('<img>').attr('src', './images/' + item.id + '/1.jpg');
                        var productName = $('<h4>').text(item.nome);
                        link.append(image, productName);
                        var internalDiv = $('<div>').addClass('internal-div');
                        var price = $('<span>').text(item.prezzo + '€');
                        var heartButton = $('<button>').addClass('heart-button');
                        var heartIcon = $('<img>').attr('src', 'icons/heartIcon.svg');
                        var clickedHeartIcon = $('<img>').attr('src', 'icons/heartIconClicked.svg').css('display', 'none');
                        heartButton.append(heartIcon, clickedHeartIcon);
                        internalDiv.append(price, heartButton);
                        card.append(link, internalDiv);
                        container.append(card)
                    });
                    handlePreferiti();
                    handleClickPreferiti();
                });
        }
    );
}

$(document).ready(() => {
    handlePreferiti();
    handleClickPreferiti();
    handleSearch();
});

