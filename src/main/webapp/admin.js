$(document).ready(() => {
    $('.Aggiorna').click(function () {
        var prezzo = $(this).siblings('input[name="prezzo"]').val();
        var quantita = $(this).siblings('select').val();
        var productId = $(this).closest('.product-line').attr('id').replace("prodotto-", "");

        console.log('Prezzo:', prezzo);
        console.log('Quantità:', quantita);
        console.log('ID Prodotto:', productId);
        $.post("aggiornaProdotto", {"id": productId, "prezzo": prezzo, "quantita": quantita});
    });
})