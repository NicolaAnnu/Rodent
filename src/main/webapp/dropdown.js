document.addEventListener('DOMContentLoaded', () => {
    document.querySelector('.dropdown-button').addEventListener('click', () => {
        const smallNavigation = document.querySelector('.small-navigation');
        smallNavigation.style.display = smallNavigation.style.display === 'none' || smallNavigation.style.display === '' ? 'block' : 'none';
    });
});
