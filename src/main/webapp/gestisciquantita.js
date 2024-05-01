$(document).ready(() => {
        function updatePrice() {
            let totale = 0.0;
            $('.Cart-Items').each(function () {
                var conteggio = parseFloat($(this).find('.count').text());
                var importo = parseFloat($(this).find('.amount').text());
                totale += conteggio * importo;
            });
            $(".total-amount").text(totale.toFixed(2));
            var spanElement = $('<span>').addClass('euro');
            spanElement.text("€");
            $(".total-amount").append(spanElement);

            var numeroOggetti = $(".Cart-Items").length;
            $(".items").text(numeroOggetti + " oggetti");
        }

        $(".btn").on("click", function () {
            var counter = $(this).closest(".counter");
            var countElement = counter.find(".count");
            var countValue = parseInt(countElement.text());
            if (countValue < 1) {
                return;
            }
            if ($(this).hasClass("plus")) {
                countValue += 1;
            } else if ($(this).hasClass("minus")) {
                countValue -= 1;
            }
            let productId = $(counter).data("product");
            $.post("aggiornaQuantita", {"id": productId, "quantita": countValue});
            if (countValue == 0) {
                $(counter).parent().remove();
                if ($(".Cart-Items").length == 0) {
                    location.reload();
                }
            } else {
                countElement.text(countValue);
            }
            updatePrice();
        });

    }
);


