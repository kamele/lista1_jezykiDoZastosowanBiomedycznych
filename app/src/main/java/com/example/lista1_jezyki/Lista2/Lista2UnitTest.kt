package com.example.lista1_jezyki.Lista2

import com.example.lista1_jezyki.Lista2.lista2Sequence.DNASequence
import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.assertThrows

class Lista2UnitTestWielomiany {
    //constructor tests
    @Test
    fun constructorCorrectData() {
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(w.wspolczynniki, wspolczynniki)
    }

    @Test
    fun constructorIllegalArgumentExceptionZeroInWspolczynnikiStart() {
        assertThrows<IllegalArgumentException>("Współczynnik przy najwyższej potędze wielomianu nie moze byc zero") {
            val wspolczynniki = intArrayOf(0, 2, 3, 4)
            Wielomian(wspolczynniki)
        }
    }

    @Test
    fun constructorIllegalArgumentExceptionEmptyArray() {
        assertThrows<IllegalArgumentException>("Podano pustą liste współczynników, należy podać przynajmniej 1") {
            val wspolczynniki = intArrayOf()
            Wielomian(wspolczynniki)
        }
    }

    //stopien tests
    @Test
    fun stopienIsCorrect() {
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(3, w.stopien())
    }
    @Test
    fun stopienIsCorrectZero() {
        val wspolczynniki = intArrayOf(4)
        val w = Wielomian(wspolczynniki)
        assertEquals(0, w.stopien())
    }

    @Test
    fun stopienIsCorrectOnlyBiggestWspolczynnik() {
        val wspolczynniki = intArrayOf(1,0,0)
        val w = Wielomian(wspolczynniki)
        assertEquals(2, w.stopien())

    }

    // tekstWersjaWielomianu tests
    @Test
    fun tekstWersjaWielomianuOnlyOneElement() {
        val wspolczynniki = intArrayOf(1)
        val w = Wielomian(wspolczynniki)
        assertEquals("W(x) =1", w.tekstWersjaWielomianu())
    }
    @Test
    fun tekstWersjaWielomianuOnlyOneElementAndIsZero() {
        val wspolczynniki = intArrayOf(0)
        val w = Wielomian(wspolczynniki)
        assertEquals("W(x) =0", w.tekstWersjaWielomianu())
    }
    @Test
    fun tekstWersjaWielomianuFirstPower(){
        val wspolczynniki = intArrayOf(1, 2)
        val w = Wielomian(wspolczynniki)
        assertEquals("W(x) =1 * x + 2", w.tekstWersjaWielomianu())
    }
    @Test
    fun tekstWersjaWielomianuBigPower(){
        val wspolczynniki = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val w = Wielomian(wspolczynniki)
        assertEquals("W(x) =1 * (x^9) + 2 * (x^8) + 3 * (x^7) + 4 * (x^6) + 5 * (x^5) + 6 * (x^4) + 7 * (x^3) + 8 * (x^2) + 9 * x + 10", w.tekstWersjaWielomianu())
    }
    @Test
    fun tekstWersjaWielomianuSkipZeros(){
        val wspolczynniki = intArrayOf(1, 0, 0, 0, 0)
        val w = Wielomian(wspolczynniki)
        assertEquals("W(x) =1 * (x^4)", w.tekstWersjaWielomianu())
    }

    //obliczWartosc tests
    @Test
    fun obliczWartoscIsCorect() {
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(58.0, w.obliczWartosc(3.0), 0.01)
    }
    @Test
    fun obliczWartoscIsCorectZero() {
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(4.0, w.obliczWartosc(0.0), 0.01)
    }
    @Test
    fun obliczWartoscIsCorectNegative() {
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(2.0, w.obliczWartosc(-1.0), 0.01)
    }

    @Test
    fun invokeIsCorect(){
        val wspolczynniki = intArrayOf(1, 2, 3, 4)
        val w = Wielomian(wspolczynniki)
        assertEquals(58.0, w(3.0), 0.01)
    }

    @Test
    fun plusIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        val w3 = w1 + w2
        assertArrayEquals(intArrayOf(-1, 3, 1), w3.wspolczynniki)
    }
    @Test
    fun minusIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        val w3 = w1 - w2
        assertArrayEquals(intArrayOf(1, 1, 7), w3.wspolczynniki)
    }
    @Test
    fun timesIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        val w3 = w1 * w2
        assertArrayEquals(intArrayOf(-12,-5,-2,1), w3.wspolczynniki)
    }
    @Test
    fun plusAssignIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        w1 += w2
        assertArrayEquals(intArrayOf(-1, 3, 1), w1.wspolczynniki)
    }
    @Test
    fun minusAssignIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        w1 -= w2
        assertArrayEquals(intArrayOf(1, 1, 7), w1.wspolczynniki)
    }
    @Test
    fun timesAssignIsCorrect(){
        val w1 = Wielomian(intArrayOf( 1, 2, 3))
        val w2 = Wielomian(intArrayOf(1, -4))
        w1 *= w2
        assertArrayEquals(intArrayOf(-12,-5,-2,1), w1.wspolczynniki)
    }


}

class Lista2UnitTest{
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun isCorrect() {
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
        val actualTranskrybuj = dna3.transcribe().data
        assertEquals(expectedTranskrybuj, actualTranskrybuj)
    }
   
}