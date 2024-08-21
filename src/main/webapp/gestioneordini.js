document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.aggiorna-ordine-btn').forEach(button => {
        button.addEventListener('click', function() {
            var riga = this.closest(".riga");
            var status = riga.querySelector('select').value;
            var idOrdine = riga.querySelector('.id-ordine').textContent;

            console.log('status:', status);
            console.log('idOrdine:', idOrdine);

            fetch('gestioneOrdini', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: `id=${encodeURIComponent(idOrdine.trim())}&status=${encodeURIComponent(status)}`
            });
        });
    });
});
