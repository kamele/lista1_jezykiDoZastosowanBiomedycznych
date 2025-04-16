package com.example.lista1_jezyki.Lista2.lista2Sequence

open class Sequence(
    var identifier:Int,
    var data:String,
    val VALID_CHARS: ArrayList<Char>
) {
    private var length: Int
//    open val VALID_CHARS: ArrayList<Char> = arrayListOf('A', 'G', 'C', 'T')

    init {
        data.uppercase().forEach { it ->
            if (it !in VALID_CHARS) {
                throw IllegalArgumentException("Wartości muszą należeć do liter A, G, C i T")
            }
        }
        this.data = data.uppercase()
        this.length = this.data.length
    }

    //funkcja nadpisuje toString i zwraca wynik w formacie FASTA_format
    //użyto https://pl.wikipedia.org/wiki/FASTA_format
    override fun toString(): String {
        return ">$identifier \n $data"
    }

    //Funkcja zmienia zasadę na zadanej pozycji w sekwencji DNA na zasadę podaną jako value
    //@throws funkcja rzuca wyjątek IllegalArgumentException gdy nić zawiera inne litery niż A,T,C,G
    // lub gdy pozycia jest poza zakresem date
    fun  mutate(position:Int, value:Char){
        if(value in VALID_CHARS){
            if(position>=0 && position<length){
                data=data.substring(IntRange(0,position-1))+value+data.substring(IntRange(position+1, length-1))
            }else{
                throw IllegalArgumentException("Pozycja poza zakresem zasad")
            }
        }else{
            throw IllegalArgumentException("Wartości muszą należeć do liter A, G, C i T")
        }

    }

    //Funkcja zwraca pozycję zadanego motywu w sekwencji DNA
    //@throws funkcja rzuca wyjątek IllegalArgumentException gdy motif zawiera inne litery niż A,T,C,G
    //użyto https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.text/index-of.html
    fun findMotif(motif:String):Int{
        motif.uppercase().forEach { it ->
            if(!(it in VALID_CHARS)){
                throw IllegalArgumentException("Wartości muszą należeć do liter A, G, C i T")
            }
        }
        return data.indexOf(motif.uppercase())

    }
}