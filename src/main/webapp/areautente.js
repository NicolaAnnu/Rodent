$(document).ready(() => {
    $(".menu-options li a").click(function () {
        $(".menu-options li").removeClass("selected");
        $(this).parent("li").addClass("selected");
        $(".menu-item").hide();
        let target = $(this).parent("li").text().replace(" ", "-");
        $(`#${target}.menu-item`).show();
        if (target.toLowerCase() === "preferiti") {
            $("#preferiti").load("preferiti.jsp")
        }
        if (target.toLowerCase() === "ordini") {
            $("#ordini").load("ordini.jsp");
        }
    });
});