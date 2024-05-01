use rodent;
INSERT INTO Utente (ruolo, username, email, password, nome, cognome, num_telefono, cap, civico, via)
VALUES 
(1, 'utente1', 'utente1@example.com', '$2a$12$XY7neW6MRwlkg8I2/ToR.uSWHSBQOlQ0pSH7f1Vv9lHh/SjtlbmKK', 'Nome1', 'Cognome1', '1234567890', '12345', '1', 'Via esempio 1'),
(1, 'utente2', 'utente2@example.com', '$2a$12$xuU7fsy4Xn37afUiF/Ezpu8tYyI0Uy7oLUlKeRdJpAph0JnV1Oq46', 'Nome2', 'Cognome2', '1234567890', '12345', '2', 'Via esempio 2');

INSERT INTO Categoria (nome)
VALUES
('Categoria 1'),
('Categoria 2'),
('Categoria 3'),
('Categoria 4');

INSERT INTO Prodotto (id, nome,descrizione, prezzo, disponibilita, categoria) VALUES
(1, "Gabbia per Conigli", "Gabbia per conigli grigia: spaziosa, sicura,ideale per l''allevamento domestico." , 30, 12, 1),
(2, "Gabbia per Conigli", "Gabbia per conigli blu scuro con recipiente e manico rosa, ideale per conigli di qualsiasi dimensione", 35, 23, 1),
(3, "Gabbia per Roditori", "Gabbia per roditori capiente, rivestimento in metallo, nero scuro", 40, 22, 1),
(4, "Gabbia per Porcellino d'India", "Gabbia per Roditori e porcellini d'India, compreso di rivestimento cotone blu, sicura, ideale anche per qualsiasi tipo di roditore", 40, 22, 1),
(5, "Gabbia per Roditori", "Gabbia per Roditori, 5 piani, capiente per più roditori", 80, 30, 1),
(6, "Gabbia per Porcellini D'India", "Gabbia per Porcellini D'India, capiente, sicura, con tana e piano rialzato", 40, 22, 1),
(7, "Gabbia per Criceti", "Gabbia per Criceti o roditori di piccole dimensioni, compresa di piano e tubo.", 42, 22, 1),
(8, "Recinto per Roditori", "Ampio recinto per Roditori, Consigliato da utilizzare per Esterno, sicuro, ideale per l'allevamento domestico", 25, 12, 1),
(9, "Box per Roditori", "Box per Roditori, eccellente e facile da montare, facile da trasportare e tessuto comodo", 50, 24, 1),
(10, "Gabbia per Conigli", "Ampia Gabbia per Conigli, Lusso per i conigli e per un soggiorno piacevole con piano rialzato e ripiano per poter poggiare tutto l'occorrente", 200, 12, 1),
(11, "Casetta per Roditori", "Ampia casetta di legno per Roditori, compreso di recinto esterno, piacevole e sicura, consigliata per esterno", 120, 14, 1),
(12, "Casetta per Roditori di piccole dimensioni", "Casetta di legno per roditori di piccole dimensioni, perfetta per criceti, confortevole", 45, 13, 1),
(13, "Gabbia per Criceti", "Gabbia per criceti compreso di ruota, piacevole", 14, 19, 1),
(14, "Gabbia per topi domestici", "Gabbia per topi domestici ideale, due piani, compreso di tubo", 30, 23, 1),
(15, "Casetta per Conigli", "Il top del top per il vostro coniglietto domestico, ampia casetta rosa per il vostro coniglio, confortevole", 200, 12, 1),
(16, "Pellet per Roditori", "Pellet naturale, 0 prodotti chimici", 6, 80, 2),
(17, "Shampoo insetticida", "Shampoo insetticida perfetto per qualsiasi roditore, elimina insetti", 7, 34, 2),
(18, "Elimina Odori", "Spray elimina Odori microbiologico", 10, 83, 2),
(19, "Multivitaminico per roditori", "Multivitaminico per roditori, 2 gocce per roditore, elimina insetti e pulci", 12, 34, 2),
(20, "Lettiera", "Lettiera per roditori, no prodotti chimici", 12, 32, 2),
(21, "Spazzola per Roditori", "Efficace",8, 32, 2),
(22, "Shampoo per Conigli", "Attenzione usare con cura, prodotto 100% naturale, made in Germany", 15, 32, 2),
(23, "Kit per pulizia Roditori", "Il kit comprende: Un porta attrezzi, una spazzola, un taglia unghie, un guanto per rimozione di peli in eccesso, una spazzola per pulci, ed attrezzi aggiuntivi per tagliaunghie e per rimuovere pulci", 30, 23, 2),
(24, "Lettiera aromatizzata per Criceti", "Aromatizzata alla mela", 15, 12, 2),
(25, "Panno Waterpoof", "Panno efficace per pulire liquidi dei roditori", 10, 32, 2),
(26, "Tessuto per Bisogni", "Tessuto per bisogni utile per qualsiasi tipo di roditore", 5, 23, 2),
(27, "Kit Rosa per Roditori", "Kit per roditori efficace compreso di: spazzola normale, spazzola per pulci, forbicina, spazzola piccola, tagliaunghie e spazzola forte",24, 32, 2),
(28, "Shampoo Aloe Vera", "Shampoo aloe vera, efficace per conigli", 18, 23, 2),
(29, "Guanto per Roditori", "Guanto togli peli in eccesso e insetti", 10, 21, 2),
(30, "Kit Pulizia Roditore", "Kit perfetto per pulizia per i vostri roditori, il set comprende: Un guanto, un tagliaunghie, una forbicina e una spazzola", 18, 34, 2),
(31, "Mangime misto", "Mangime completo classico per conigli nani con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 10, 49, 3),
(32, "Fieno", "Fieno erbe secche super nutrienti per roditori", 4, 84, 3),
(33, "Mangime misto per Criceti", "Mangime completo classico con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 8, 34, 3),
(34, "Mangime misto", "Mangime completo classico per conigli nani con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 15, 34, 3),
(35, "Mangime misto per Roditori", "Mangime completo classico per criceti con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 15, 34, 3),
(36, "Mangime misto per Roditori", "Mangime completo classico per criceti con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 8, 34, 3),
(37, "Mangime misto per Conigli", "Mangime completo classico per conigli nani di Vitakraft con prelibati semi e verdure; senza zucchero, aromi e conservanti artificiali", 10, 32, 3),
(38, "Fieno con Camomilla", "Fieno con camomilla perfetto per coniglio nano", 6, 60, 3),
(39, "Paglianatura", "Fieno di Prato", 10, 45, 3),
(40, "Mangime per Roditori", "Mangime con presenza di girasole", 10, 45, 3),
(41, "Fieno 10 kg", "Fieno di 10 kg, fieno di prato", 22, 34, 3),
(42, "Mini Pop Corn Quiko", "Mini pop corn da sgranocchiare perfetto per roditori", 8, 34, 3),
(43, "Snack carotine", "Snack carotine per conigli", 12, 34, 3),
(44, "Mangime per topi domestici", "Mangime perfetto per topi domestici, completo", 13, 45, 3),
(45, "Snack Grainless per Porcellini d'India", "Mangime completo per porcellini d'India, adatto anche per piccoli e grandi roditori", 12, 43, 3),
(46, "Borraccia per Roditori", "Borraccia 50ml", 5, 34, 4),
(47, "Borraccia per Criceti integrato di Tubo", "Borraccia per criceti con tubo integrato, ATTENZIONE!! SOLO PER CRICETI DI PICCOLE DIMENSIONI", 13, 43, 4),
(48, "Ciotola per Criceti e piccoli Roditori", "Ciotola di plastica piccola per piccoli roditori", 8, 43, 4),
(49, "Ciotola forma di Carota per Conigli", "Ciotola perfetta per conigli forma di Carota", 12, 42, 4),
(50, "Kit Tubo Borraccia Criceti", "Tubo per Criceti compreso di borraccia", 15, 53, 4),
(51, "Tubo per Criceti", "4 pezzi di tubi collegabili", 12, 43, 4),
(52, "Ruota per Criceti", "Ruota divertente per Criceti", 10, 42, 4),
(53, "Tubo per Conigli con giocattoli", "Tubo di stoffa con accessori divertenti per coniglietti", 15, 43, 4),
(54, "Casetta invernale per Roditori", "Casetta Invernale honey per coniglietti e piccoli roditori", 20, 32, 4),
(55, "Casetta invernale per Criceti", "Casetta invernale beerus per criceti", 17, 34, 4),
(56, "Palla per Criceti", "Divertente palla di plastica per Criceti, compatta", 10, 32, 4),
(57, "Pettorina Regolabile per Conigli", "2 Pezzi Imbracatura Morbida a Rete Confortevole Imbracatura per Coniglio con Elastico Guinzaglio per Piccoli Animali Conigli criceti", 23, 32, 4),
(58, "Pettorina Regolabile per Roditori di Piccole dimensioni", "Imbarcatura mobile, confortevole con elastico", 15, 43, 4),
(59, "Trasportino per Conigli", "Trasportino di tessuto resistente ad acqua, perfetto per conigli", 35, 32, 4),
(60, "Trasportino per Roditori di piccole dimensioni", "Trasportino di plastica per conigli nani e per roditori di piccole dimensioni, confortevole, sicuro", 19, 32, 4);


INSERT INTO Sconto (codice, valore, scadenza, utilizzi)
VALUES
('CODICE1', 10.0, '2024-12-31', 100),
('CODICE2', 15.0, '2024-12-31', 200),
('CODICE3', 20.0, '2024-12-31', 150),
('CODICE4', 12.5, '2024-12-31', 50),
('CODICE5', 8.0, '2024-12-31', 300),
('CODICE6', 5.0, '2024-12-31', 250),
('CODICE7', 18.0, '2024-12-31', 75),
('CODICE8', 11.5, '2024-12-31', 175),
('CODICE9', 14.0, '2024-12-31', 225),
('CODICE10', 9.5, '2024-12-31', 125);


