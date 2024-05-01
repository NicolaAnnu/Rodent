$(document).ready(function () {
    $("#checkout-form").submit(function (event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            url: "checkout",
            type: "POST",
            data: formData,
            contentType: "application/x-www-form-urlencoded",
            success: function (response) {
                window.location.href = "index.jsp";
            },
            error: function (xhr, status, error) {
                alert(JSON.stringify(error));
                alert("Alcuni prodotti non erano disponibili, la pagina verrà ricaricata");
                window.location.reload();
            }
        });
    });
});