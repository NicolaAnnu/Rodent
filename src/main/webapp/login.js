$(document).ready(function () {
    $("#login-form").submit(function (event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            url: "login",
            type: "POST",
            data: formData,
            contentType: "application/x-www-form-urlencoded",
            success: function (response) {
                window.location.reload();
            },
            error: function (xhr, status, error) {
                $(".messaggio-errore").slideToggle(400);
                setTimeout(() => {
                    $(".messaggio-errore").slideToggle(400);
                }, 2000)
            }
        });
    });
});