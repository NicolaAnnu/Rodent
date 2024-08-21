document.addEventListener('DOMContentLoaded', () => {
    const menuOptions = document.querySelectorAll('.menu-options li a');
    menuOptions.forEach(menuOption => {
        menuOption.addEventListener('click', function() {
            // Rimuove la classe 'selected' da tutti gli elementi
            document.querySelectorAll('.menu-options li').forEach(li => {
                li.classList.remove('selected');
            });
            // Aggiunge la classe 'selected' all'elemento cliccato
            this.parentElement.classList.add('selected');
            // Nasconde tutti gli elementi del menu
            document.querySelectorAll('.menu-item').forEach(menuItem => {
                menuItem.style.display = 'none';
            });
            // Recupera il testo dell'elemento cliccato e lo usa per mostrare il contenuto
            let target = this.parentElement.textContent.trim().replace(" ", "-");
            const targetMenuItem = document.getElementById(target);
            if (targetMenuItem) {
                targetMenuItem.style.display = 'block';
            }
            // Carica le pagine JSP specifiche, se necessario
            if (target.toLowerCase() === 'preferiti') {
                document.getElementById('preferiti').innerHTML = ''; // Opzionale: pulizia contenuto precedente
                fetch('preferiti.jsp')
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById('preferiti').innerHTML = data;
                    });
            }
            if (target.toLowerCase() === 'ordini') {
                document.getElementById('ordini').innerHTML = ''; // Opzionale: pulizia contenuto precedente
                fetch('ordini.jsp')
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById('ordini').innerHTML = data;
                    });
            }
        });
    });
});
