document.addEventListener('DOMContentLoaded', function () {
    var currentIndex = 0;
    var slides = document.querySelectorAll('.slide');
    var totalSlides = slides.length;

    slides.forEach(function (slide) {
        slide.style.display = 'none';
    });
    slides[currentIndex].style.display = 'block';

    function nextSlide() {
        slides[currentIndex].style.display = 'none';
        currentIndex = (currentIndex + 1) % totalSlides;
        slides[currentIndex].style.display = 'block';
    }

    function prevSlide() {
        slides[currentIndex].style.display = 'none';
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        slides[currentIndex].style.display = 'block';
    }

    document.querySelector('.next-button').addEventListener('click', function () {
        nextSlide();
    });

    document.querySelector('.prev-button').addEventListener('click', function () {
        prevSlide();
    });
});
