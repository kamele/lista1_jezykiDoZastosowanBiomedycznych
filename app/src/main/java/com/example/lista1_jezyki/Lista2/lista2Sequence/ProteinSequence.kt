package com.example.lista1_jezyki.Lista2.lista2Sequence

class ProteinSequence(
    identifier:Int,
    data:String,
    VALID_CHARS: ArrayList<Char> = arrayListOf(
        'A', // Alanina
        'C', // Cysteina
        'D', // Kwas asparaginowy
        'E', // Kwas glutaminowy
        'F', // Fenylalanina
        'G', // Glicyna
        'H', // Histydyna
        'I', // Izoleucyna
        'K', // Lizyna
        'L', // Leucyna
        'M', // Metionina
        'N', // Asparagina
        'P', // Prolina
        'Q', // Glutamina
        'R', // Arginina
        'S', // Seryna
        'T', // Treonina
        'U', // Selenocysteina
        'V',  // Walina
        'W', // Tryptofan
        'Y', // Tyrozyna
        '-' // Kodon terminacyjny
    )
): Sequence(identifier, data, VALID_CHARS){
    //przy definiowaniu dozwolonych znaków korzystano z https://pl.wikipedia.org/wiki/Aminokwasy_bia%C5%82kowe
}