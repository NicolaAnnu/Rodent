$(document).ready(function () {
    var currentIndex = 0;
    var slides = $('.slide');
    var totalSlides = slides.length;
    slides.hide();
    slides.eq(currentIndex).show();

    function nextSlide() {
        slides.eq(currentIndex).hide();
        currentIndex = (currentIndex + 1) % totalSlides;
        slides.eq(currentIndex).fadeIn();
    }

    function prevSlide() {
        slides.eq(currentIndex).hide();
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        slides.eq(currentIndex).fadeIn();
    }


    // Azioni dei pulsanti "Avanti" e "Indietro"
    $('.next-button').click(function () {
        nextSlide();
    });

    $('.prev-button').click(function () {
        prevSlide();
    });
});