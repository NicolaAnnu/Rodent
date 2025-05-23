$(document).ready(() => {
    $('.aggiorna-ordine-btn').click(function () {
        var riga = $(this).closest(".riga");
        var status = $(riga).find('select').val();
        var idOrdine = $(riga).find('.id-ordine').text();

        console.log('status:', status);
        console.log('idOrdine:', idOrdine);
        $.post("gestioneOrdini", {"id": idOrdine.trim(), "status": status});
    })
})