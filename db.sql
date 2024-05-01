DROP DATABASE if exists rodent;
CREATE DATABASE rodent;
use rodent;
create table Utente (
    id int auto_increment not null,
    ruolo int not null,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    nome varchar(255) not null,
    cognome varchar(255) not null,
    num_telefono varchar(255) not null,
    cap varchar(5) not null,
    civico varchar(5) not null,
    via varchar(255) not null,
    primary key(id)
);

create table Categoria(
    id int auto_increment not null,
    nome varchar(255) not null,
    primary key(id)
);

create table Prodotto(
    id int auto_increment not null,
    nome varchar(255) not null,
    descrizione varchar(1500) not null,
    prezzo float not null,
    disponibilita int not null,
    categoria int not null,
    primary key(id),
    foreign key (categoria) references Categoria(id)
);

create table Preferiti(
    id_utente int not null,
    id_prodotto int not null,
    foreign key (id_utente) references Utente(id),
    foreign key (id_prodotto) references Prodotto(id),
    primary key (id_utente, id_prodotto)
);

create table Materiale(
    materiale varchar(255) not null,
    id_prodotto int not null,
    foreign key (id_prodotto) references Prodotto(id),
    primary key (materiale, id_prodotto)
);

create table Sconto(
    codice varchar(255) not null,
    valore float not null,
    scadenza date,
    utilizzi int,
    primary key(codice)
);

create table Carrello(
    id int auto_increment not null,
    sconto varchar(255),
    utente int not null,
    foreign key(utente) references Utente(id),
    foreign key(sconto) references Sconto(codice),
    primary key(id, utente)
);

create table Prodotti_in_carrello(
    id_carrello int not null,
    id_utente int not null,
    id_prodotto int not null,
    quantita int not null,
    foreign key(id_carrello,id_utente) references Carrello(id, utente),
    foreign key(id_prodotto) references Prodotto(id),
    primary key(id_carrello, id_utente, id_prodotto)
);

create table Ordine(
    id int auto_increment,
    id_carrello int not null,
    id_utente int not null,
    metodo_di_pagamento varchar(255) not null,
    stato varchar(255) not null,
    cap varchar(5) not null,
    civico varchar(5) not null,
    via varchar(255) not null,
    foreign key(id_carrello, id_utente) references Carrello(id, utente),
    primary key(id, id_carrello, id_utente)
)