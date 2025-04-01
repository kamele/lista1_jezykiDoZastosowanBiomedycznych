package com.example.lista1_jezyki
import org.junit.Test
import org.junit.Assert.*
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.assertThrows
import com.example.lista1_jezyki.heron as heron

class HeronTest {
    @Test
    fun resultIsCorrect() {
        val sideA = 6.0
        val sideB = 8.0
        val sideC = 10.0
        val expectedArea = 24.0
        val actualArea = heron(sideA, sideB, sideC)
        assertEquals(expectedArea, actualArea)
    }
    @Test
    fun argsIllegalArgumentNotTriangle() {
        val resultException = assertThrows<IllegalArgumentException> {
            heron(1.0,1.0,1.0)
        }
        assertEquals("Podane boki nie nie tworzą trójkąta", resultException.message)
    }
    @Test
    fun argsIllegalArgumentNegativeSideLength() {
        val resultException = assertThrows<IllegalArgumentException> {heron(1.0,1.0,1.0)}
        assertEquals("Wartość długości nie mogą być liczbami ujemnymi", resultException.message)
    }

}
class CommonTest {
    @Test
    fun resultIsCorrect() {
        val x= intArrayOf(1,2,2,3,3,3,6,7)
        val y= intArrayOf(1,1,2,3,4,6,7)

        val expectedCommon = ArrayList<Int>(listOf(1,2,3,6,7))
        val actualCommon = common(x,y)
        assertEquals(expectedCommon, actualCommon)
    }
    @Test
    fun resultOfEmptyCollection() {
        val x= intArrayOf(1,2,2,3,3,3,6,7)
        val y= intArrayOf()

        val expectedCommon = ArrayList<Int>(listOf())
        val actualCommon = common(x,y)
        assertEquals(expectedCommon, actualCommon)
    }


}
class SubSetsTest {
    @Test
    fun resultIsCorrect() {
        val xSet = setOf(1,2,3)
        val expectedSubSets = setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1,2), setOf(1,3), setOf(2,3), setOf(1,2,3))
        val actualSubSets = subSets(xSet)

        assertEquals(expectedSubSets, actualSubSets)
        println(subSets(xSet))
    }
    @Test
    fun resultForEmptySet() {
        val xSet = emptySet<Int>()
        val expectedSubSets = setOf(emptySet<Int>())
        val actualSubSets = subSets(xSet)

        assertEquals(expectedSubSets, actualSubSets)
        println(subSets(xSet))
    }
}
class FibIterTest {
    @Test
    fun resultIsCorrect() {
        val n = 4
        val expectedfibIter = ArrayList<Int>(listOf(0,1,1,2))//n=4
        val actualfibIter = fibIter(n)
        assertEquals(expectedfibIter, actualfibIter)
    }
    @Test
    fun resultForOne() {
        println("iteracje")
        val n = 1
        val expectedfibIter = ArrayList<Int>(listOf(0))//n=1
        val actualfibIter = fibIter(n)
        assertEquals(expectedfibIter, actualfibIter)
    }
    @Test
    fun resultForTwo() {
        println("iteracje")
        val n = 2
        val expectedfibIter = ArrayList<Int>(listOf(0,1))//n=2
        val actualfibIter = fibIter(n)
        assertEquals(expectedfibIter, actualfibIter)
        println(fibIter(n))
    }
    @Test
    fun argsIllegalArgumentNegativeN() {
        val resultException = assertThrows<IllegalArgumentException> {
            fibIter(-1)
        }
        assertEquals("Wartosc n nie moze byc ujemna lub rowna zero", resultException.message)
    }


}
class FibRecTest {
    @Test
    fun resultIsCorrect() {
        val n = 4
        val expectedfibRec = ArrayList<Int>(listOf(0,1,1,2))//n=4
        val actualfibRec = fibRec(n)
        assertEquals(expectedfibRec, actualfibRec)
    }
    @Test
    fun resultForOne() {
        println("iteracje")
        val n = 1
        val expectedfibRec = ArrayList<Int>(listOf(0))//n=1
        val actualfibRec = fibRec(n)
        assertEquals(expectedfibRec, actualfibRec)
    }
    @Test
    fun resultForTwo() {
        println("iteracje")
        val n = 2
        val expectedfibRec = ArrayList<Int>(listOf(0,1))//n=2
        val actualfibRec = fibRec(n)
        assertEquals(expectedfibRec, actualfibRec)
    }
    @Test
    fun argsIllegalArgumentNegativeN() {
        val resultException = assertThrows<IllegalArgumentException> {
            fibRec(-1)
        }
        assertEquals("Wartosc n nie moze byc ujemna lub rowna zero", resultException.message)
    }


}
class CollatzTest {
    @Test
    fun resultIsCorrect() {
        val c0=11
        val expectedCollatz = ArrayList<Int>(listOf(11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1))//n=4
        val actualCollatz = collatz(c0)
        assertEquals(expectedCollatz, actualCollatz)
    }
    @Test
    fun argsIllegalArgumentNegativeC() {
        val resultException = assertThrows<IllegalArgumentException> {
            collatz(-1)
        }
        assertEquals("Wartosc c nie moze byc ujemna lub rowna zero", resultException.message)
    }
}
class KomplementTest {
    @Test
    fun resultIsCorrect() {
        val dnaStrand="AAGCTG"
        val expectedKomplement = "CAGCTT"
        val actualKomplement = komplement(dnaStrand)
        assertEquals(expectedKomplement, actualKomplement)
    }
    @Test
    fun argsIllegalArgumentWrongLetter() {
        val resultException = assertThrows<IllegalArgumentException> {
            komplement("AAGSTG")
        }
        assertEquals("Wartosc w nici jest inna niz A, T, C i G", resultException.message)
    }
}
class TranskrybujTest {
    @Test
    fun resultIsCorrect() {
        val dnaStrand="TTCGAC"
        val expectedTranskrybuj = "AAGCUG"
        val actualTranskrybuj = transkrybuj(dnaStrand)
        assertEquals(expectedTranskrybuj, actualTranskrybuj)
    }
    @Test
    fun argsIllegalArgumentWrongLetter() {
        val resultException = assertThrows<IllegalArgumentException> {
            transkrybuj("AAGSTG")
        }
        assertEquals("Wartosc w nici jest inna niz A, T, C i G", resultException.message)
    }
}
