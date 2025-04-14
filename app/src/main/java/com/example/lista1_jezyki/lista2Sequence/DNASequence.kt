package com.example.lista1_jezyki.lista2Sequence

import com.example.lista1_jezyki.komplement
import com.example.lista1_jezyki.transkrybuj
import org.junit.Assert.assertEquals

class DNASequence(
    var identifier:Int,
    var data:String
) {
    private var length: Int
    val VALID_CHARS: ArrayList<Char> = arrayListOf('A','G','C','T')

    init{
        data.uppercase().forEach { it ->
            if(!(it in VALID_CHARS)){
                throw IllegalArgumentException("Wartości muszą należeć do liter A, G, C i T")
            }
        }
        this.data = data.uppercase()
        this.length = this.data.length
    }

    //funkcja nadpisuje toString i zwraca wynik w formacie FASTA_format
    //użyto https://pl.wikipedia.org/wiki/FASTA_format
    override fun toString():String{
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

    /**
     * Funkcja dla sekwencji nici kodującej DNA znajduje i zwraca sekwencję
     * nici matrycowej
     * @return Funcja zwraca sekwencję nici matrycowej
     */
    fun complement():String{
        var result = ""
        for(s in data){
            val elem = when(s){
                'A'->'T'
                'T'->'A'
                'C'->'G'
                'G'->'C'
                else -> {
                    throw IllegalArgumentException("Wartosc w nici jest inna niz A, T, C i G")
                }
            }
            result+=elem
        }
        return result.reversed()
    }

    /**
     * Funkcja dla sekwencji nici matrycowej DNA znajduje i zwraca sekwencję RNA
     * @return Funcja zwraca sekwencję RNA
     */
    fun transcribe():String{
        var result = ""
        for(s in data){
            val elem = when(s){
                'A'->'U'
                'T'->'A'
                'C'->'G'
                'G'->'C'
                else -> {
                    throw IllegalArgumentException("Wartosc w nici jest inna niz A, T, C i G")
                }
            }
            result+=elem
        }
        return result.reversed()
    }
}

fun main(){
    var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
    println(dna.toString())//AATTCGATCG
    dna.mutate(0, 'T')
    println(dna.toString())//TATTCGATCG
    println(dna.findMotif("TT"))//2
    println(dna.findMotif("AG"))//-1

    println("komplement")
    val dnaStrand="AAGCTG"
    val dna2=DNASequence(2, dnaStrand)
    val expectedKomplement = "CAGCTT"
    val actualKomplement = dna2.complement()
    assertEquals(expectedKomplement, actualKomplement)

    println("transkrybuj")
    val dnaStrand2="TTCGAC"
    val dna3=DNASequence(3, dnaStrand2)
    val expectedTranskrybuj = "GUCGAA"
    val actualTranskrybuj = dna3.transcribe()
    assertEquals(expectedTranskrybuj, actualTranskrybuj)

}