var currentIndex = 0;
var slides = [];
var totalSlides = 0;

document.addEventListener("DOMContentLoaded", function () {
    slides = document.querySelectorAll('.slide');
    totalSlides = slides.length;

    slides.forEach(slide => {
        slide.style.display = 'none';
        slide.style.opacity = 0;
    });

    if (slides[currentIndex]) {
        fadeIn(slides[currentIndex]);
    }

    document.querySelectorAll('.next-button').forEach(button => {
        button.addEventListener('click', function () {
            nextSlide();
        });
    });

    document.querySelectorAll('.prev-button').forEach(button => {
        button.addEventListener('click', function () {
            prevSlide();
        });
    });
});

function fadeIn(element, duration = 400) {
    element.style.opacity = 0;
    element.style.display = "block";
    let last = +new Date();

    const tick = function () {
        let now = +new Date();
        let delta = now - last;
        let opacity = parseFloat(element.style.opacity);

        if (opacity < 1) {
            element.style.opacity = Math.min(opacity + delta / duration, 1);
            last = now;
            requestAnimationFrame(tick);
        }
    };

    tick();
}

function nextSlide() {
    slides[currentIndex].style.display = 'none';
    slides[currentIndex].style.opacity = 0;
    currentIndex = (currentIndex + 1) % totalSlides;
    fadeIn(slides[currentIndex]);
}

function prevSlide() {
    slides[currentIndex].style.display = 'none';
    slides[currentIndex].style.opacity = 0;
    currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
    fadeIn(slides[currentIndex]);
}
