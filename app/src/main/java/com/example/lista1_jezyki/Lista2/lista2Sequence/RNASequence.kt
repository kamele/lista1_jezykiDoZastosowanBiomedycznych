package com.example.lista1_jezyki.Lista2.lista2Sequence

import org.junit.Assert.assertEquals

class RNASequence(
    identifier:Int,
    data:String,
    VALID_CHARS: ArrayList<Char> = arrayListOf('A', 'G', 'C', 'U')
): Sequence(identifier, data, VALID_CHARS) {

    /**
     * Funkcja dla sekwencji nici RNA znajduje i zwraca sekwencję aminokwasow
     * @return Funcja zwraca sekwencję ProteinSequwnce
     */
    fun transcribe(): ProteinSequence {
        var result = ""
        for(i in 0 until data.length-2 step 3){
            val s = data.substring(i,i+3)
            val elem = when(s){
                "GCU","GCA","GCC","GCG" -> 'A'
                "UGU","UGC" -> 'C'
                "GAU","GAC" -> 'D'
                "GAA","GAG" -> 'E'
                "UUU","UUC" -> 'F'
                "GGU","GGC","GGA","GGG" -> 'G'
                "CAU","CAC" -> 'H'
                "AUU","AUC","AUA" -> 'I'
                "AAA","AAG" -> 'K'
                "UUA","UUG","CUU","CUC","CUA","CUG" -> 'L'
                "AUG" -> 'M'
                "AAU","AAC" -> 'N'
                "UAG" -> 'O'
                "CCU","CCC","CCA","CCG" -> 'P'
                "CAA","CAG" -> 'Q'
                "CGU","CGC","CGA","CGG","AGA","AGG" -> 'R'
                "UCU","UCC","UCA","UCG","AGU","AGC" -> 'S'
                "ACU","ACC","ACA","ACG" -> 'T'
                "UGA" -> 'U'
                "GUU","GUC","GUA","GUG" -> 'V'
                "UGG" -> 'W'
                "UAU","UAC" -> 'Y'
                "UAA","UUG" -> '-'
                else -> {
                    throw IllegalArgumentException("Wartosc w nici jest inna niz A, U, C i G lub nie tworzy poprawnej sekwencji")
                }
            }
            result+=elem
        }
        return ProteinSequence(2,result.reversed())
    }
}

fun main(){
    var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
    println(rna.toString())//AAUUCGAUCG
    rna.mutate(0, 'U')
    println(rna.toString())//UAUUCGAUCG
    println(rna.findMotif("UU"))//2
    println(rna.findMotif("AG"))//-1

    println("transkrybuj")
    val rnaStrand2="UUCGAC"
    val rna3= RNASequence(3, rnaStrand2)
    val expectedTranskrybuj = "DF"
    val actualTranskrybuj = rna3.transcribe().data
    assertEquals(expectedTranskrybuj, actualTranskrybuj)

}