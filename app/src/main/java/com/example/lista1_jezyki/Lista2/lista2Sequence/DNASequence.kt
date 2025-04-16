package com.example.lista1_jezyki.Lista2.lista2Sequence

import org.junit.Assert.assertEquals

class DNASequence(
    identifier:Int,
    data:String,
    VALID_CHARS: ArrayList<Char> = arrayListOf('A', 'G', 'C', 'T')
): Sequence(identifier, data, VALID_CHARS) {


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
    fun transcribe(): RNASequence {
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
        return RNASequence(1, result.reversed())
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
    val dna2= DNASequence(2, dnaStrand)
    val expectedKomplement = "CAGCTT"
    val actualKomplement = dna2.complement()
    assertEquals(expectedKomplement, actualKomplement)

    println("transkrybuj")
    val dnaStrand2="TTCGAC"
    val dna3= DNASequence(3, dnaStrand2)
    val expectedTranskrybuj = "GUCGAA"
    val actualTranskrybuj = dna3.transcribe()
    assertEquals(expectedTranskrybuj, actualTranskrybuj)

}