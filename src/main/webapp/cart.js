$(document).ready(() => {
    $(".product-buy-button").click(() => {
        let id = $(".product-buy-button").data("product");
        let quantita = $("#quantita").val();
        $(".product-buy-button").addClass("clicked");
        setTimeout(() => {
            $(".product-buy-button").removeClass("clicked");
        }, 300);
        $.ajax({
            url: "addToCart",
            method: "POST",
            data: {"id": id, "quantita": quantita},
            complete: (xhr, status) => {
                if (xhr.status === 409) {
                    alert("Prodotto non aggiunto, disponibilità esaurita");
                }
            }
        })
    })

})