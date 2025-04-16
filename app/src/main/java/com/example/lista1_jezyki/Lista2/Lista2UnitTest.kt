package com.example.lista1_jezyki.Lista2

import com.example.lista1_jezyki.Lista2.lista2Sequence.*
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

class Lista2UnitTestDNASequence{
    @Test
    fun constructorIsCorrect() {
        var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
        assertEquals(1, dna.identifier)
        assertEquals("AATTCGATCG", dna.data)
    }
    @Test
    fun constructorIllegalArgumentException() {
        var dna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i T") {
            DNASequence(identifier = 1, data = "AATTTCGATPP")
        }
    }
    @Test
    fun toStringIsCorrect() {
        var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
        assertEquals(">1 \n AATTCGATCG", dna.toString())
    }
    @Test
    fun mutateIsCorrect() {
        var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
        dna.mutate(0, 'T')
        assertEquals("TATTCGATCG", dna.data)
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongLetter() {
        var dna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i T") {
            var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
            dna.mutate(0, 'P')
        }
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongPosition() {
        var dna = assertThrows<IllegalArgumentException>("Pozycja poza zakresem zasad") {
            var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
            dna.mutate(12, 'A')
        }
    }
    @Test
    fun findMotifIsCorrect() {
        var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
        assertEquals(2, dna.findMotif("TT"))
    }
    @Test
    fun findMotifIllegalArgumentException() {
        var dna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i T") {
            var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
            dna.findMotif("TP")
        }
    }
    @Test
    fun findMotifNotFound() {
        var dna = DNASequence(identifier = 1, data = "AATTCGATCG")
        assertEquals(-1, dna.findMotif("AG"))
    }
    @Test
    fun complementIsCorrect() {
        val dna2= DNASequence(2, "AAGCTG")
        assertEquals("CAGCTT", dna2.complement())
    }

    @Test
    fun transcribeIsCorrect() {
        val dna3= DNASequence(3, "TTCGAC")
        assertEquals("GUCGAA", dna3.transcribe().data)
    }
}

class Lista2UnitTestRNASequence{
    @Test
    fun constructorIsCorrect() {
        var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(1, rna.identifier)
        assertEquals("AAUUCGAUCG", rna.data)
    }
    @Test
    fun constructorIllegalArgumentException() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            RNASequence(identifier = 1, data = "AAUUUCGAUPP")
        }
    }
    @Test
    fun toStringIsCorrect() {
        var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(">1 \n AAUUCGAUCG", rna.toString())
    }
    @Test
    fun mutateIsCorrect() {
        var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
        rna.mutate(0, 'U')
        assertEquals("UAUUCGAUCG", rna.data)
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongLetter() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
            rna.mutate(0, 'P')
        }
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongPosition() {
        var rna = assertThrows<IllegalArgumentException>("Pozycja poza zakresem zasad") {
            var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
            rna.mutate(12, 'A')
        }
    }
    @Test
    fun findMotifIsCorrect() {
        var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(2, rna.findMotif("UU"))
    }
    @Test
    fun findMotifIllegalArgumentException() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
            rna.findMotif("UP")
        }
    }
    @Test
    fun findMotifNotFound() {
        var rna = RNASequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(-1, rna.findMotif("AG"))
    }
    @Test
    fun transcribeIsCorrect() {
        val rna3= RNASequence(3, "UUCGAC")
        assertEquals("DF", rna3.transcribe().data)
    }
    @Test
    fun transcribeLong() {
        val rna3= RNASequence(3, "GCUGCAGCCGCGUGUUGCGAUGACGAAGAGUUUUUCGGUGGCGGAGGGCAUCACAUUAUCAUA")
        assertEquals("IIIHHGGGGFFEEDDCCAAAA", rna3.transcribe().data)
    }
}

class Lista2UnitTestProteinSequence{
    @Test
    fun constructorIsCorrect() {
        var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(1, rna.identifier)
        assertEquals("AAUUCGAUCG", rna.data)
    }
    @Test
    fun constructorIllegalArgumentException() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            ProteinSequence(identifier = 1, data = "AAUUUCGAUBB")
        }
    }
    @Test
    fun toStringIsCorrect() {
        var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(">1 \n AAUUCGAUCG", rna.toString())
    }
    @Test
    fun mutateIsCorrect() {
        var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
        rna.mutate(0, 'U')
        assertEquals("UAUUCGAUCG", rna.data)
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongLetter() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
            rna.mutate(0, 'B')
        }
    }
    @Test
    fun mutateIllegalArgumentExceptionWrongPosition() {
        var rna = assertThrows<IllegalArgumentException>("Pozycja poza zakresem zasad") {
            var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
            rna.mutate(12, 'A')
        }
    }
    @Test
    fun findMotifIsCorrect() {
        var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(2, rna.findMotif("UU"))
    }
    @Test
    fun findMotifIllegalArgumentException() {
        var rna = assertThrows<IllegalArgumentException>("Wartości muszą należeć do liter A, G, C i U") {
            var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
            rna.findMotif("UB")
        }
    }
    @Test
    fun findMotifNotFound() {
        var rna = ProteinSequence(identifier = 1, data = "AAUUCGAUCG")
        assertEquals(-1, rna.findMotif("AG"))
    }
}
