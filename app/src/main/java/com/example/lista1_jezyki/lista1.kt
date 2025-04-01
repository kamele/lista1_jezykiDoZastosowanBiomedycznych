package com.example.lista1_jezyki

import java.lang.Math.sqrt
import org.junit.Assert.*

/**
 * @author: Izabela Piasecka
 */

fun heron(a:Double, b:Double, c:Double):Double{
    /**
     * Funkcja liczy pole trójkąta na podstawie wzoru herona
     * @param a,b,c typu Double są to liczby nieujemne wyrażające długość boków trójkąta
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy boki nie tworzą trójkąta, lub są liczbami ujemnymi
     * @return Funcja zwraca warość pola trójkąta
     *
     * Korzystano z wzoru Herona: https://pl.wikipedia.org/wiki/Wz%C3%B3r_Herona
     */
    if (a<=0 || b<=0 || c<=0){
        throw IllegalArgumentException("Wartość długości nie mogą być liczbami ujemnymi")
    }
    if (a+b<c || b+c<a || c+a<b){
        throw IllegalArgumentException("Podane boki nie nie tworzą trójkąta")
    }

    val p = (a+b+c)/2
    val s = sqrt(p*(p-a)*(p-b)*(p-c))
    return s
}

fun common(x: IntArray, y: IntArray):ArrayList<Int>{
    /**
     * Funkcja  dla dwóch multizbiorów x i y zwróci ich część wspólną
     * @param x, y typu IntArray multizbiory których część wspólną szukamy
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy podane argumenty przyjmują wartość null
     * @return Funcja zwraca czesc wspólną multizbiorów
     */
// przykładowe sprawdzenie i rzucenie wyjątku, funkcja nie przyjmie takich wartości
// bo jako argumenty podane są nie null'owalne typy
//    if(x==null || y==null){
//        throw IllegalArgumentException("Podane argumenty nie mogą przyjmować wartości null")
//    }
    val common: ArrayList<Int> = ArrayList()
    if( x.isEmpty() || y.isEmpty()) return common

    //posortowanie tablic by ułatwić porównywanie
    x.sort()
    y.sort()

    var i = 0;
    var j = 0;
    println("x.size=${x.size}; y.size=${y.size}; $common")
    while(i<x.size && j<y.size){
        println("x[$i]=${x[i]}; y[$j]=${y[j]}; $common")
        if (x[i]==y[j]){
            common.add(x[i]);
            i++;
            j++;
        }else{
            if (x[i]<y[j]){
                i++;
            }else{
                j++;
            }
        }
    }
    return common
}

fun subSets(x:Set<Int>):Set<Set<Int>>{
    /**
     * Funkcja  dla dla setu x zwraca wszystkie jego podzbiory
     * @param x typu ArrayList to set którego podzbiorów szukamy
     * @return Funcja zwraca podzbiory setu x
     *
     * korzystano z GPT pytanie: podaj mi opis iteracyjnego wyznaczania wszystkich podzbiorów zbioru x zaczynając od zbioru pustego
     * Odp:
     *   Przykład
            Rozważmy zbiórX={1,2}.
            1.Początkowy zbiór pusty:
                Podzbiory={∅}
            2.Po dodaniu elementu 1: Dodajemy 1 do każdego dotychczasowego podzbioru.
            Podzbiory={∅,{1}}
            3.Po dodaniu elementu 2: Dodajemy 2 do każdego dotychczasowego podzbioru.
            Podzbiory={∅,{1},{2},{1,2}}
     */
    var subSets: Set<Set<Int>> = setOf(emptySet())
    for(xi in x){
        //iteracia dla kazdego elementu
        val subsetsIteration: MutableSet<Set<Int>> = mutableSetOf()
        for (subset in subSets) {
            //dodajemy element do wszystkich istniejacych subsetów,
            // więc będzie miał połaczenie z każdym innym elementem
            val newSubset = subset + xi
            subsetsIteration.add(newSubset)
        }
        val newSubsetsSet: Set<Set<Int>> = subsetsIteration.toSet()
        subSets = subSets + newSubsetsSet
        //dodajemy iteracje do koncowych subsetow
    }

    return subSets
}

fun fibIter(n:Int):ArrayList<Int>{
    /**
     * Funkcja licze liczby z ciagu fibonacciego metodą iteracyjną
     * @param n liczba elementów ciągu
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy n jest liczbą ujemną
     * @return Funcja zwraca liste n elementów ciągu Fibonaciego
     * Korzystano z wzoru na ciąg Fibonaciego: https://www.imaginationstationtoledo.org/about/blog/the-fibonacci-sequence
     */
    if (n<=0){
        throw IllegalArgumentException("Wartosc n nie moze byc ujemna lub rowna zero")
    }
    var fibArray = ArrayList<Int>(listOf(0))
    if(n==1) return fibArray
    fibArray.add(1)
    if(n==2) return fibArray
    for(i in 2 until n){
        fibArray.add(fibArray[i-1]+fibArray[i-2])
    }
    return fibArray
}

fun fibRec(n:Int):List<Int>{
    /**
     * Funkcja licze liczby z ciagu fibonacciego metodą rekurencyjna
     * @param n liczba elementów ciągu
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy n jest liczbą ujemną
     * @return Funcja zwraca liste n elementów ciągu Fibonaciego
     * Korzystano z wzoru na ciąg Fibonaciego: https://www.imaginationstationtoledo.org/about/blog/the-fibonacci-sequence
     */
    if (n<=0){
        throw IllegalArgumentException("Wartosc n nie moze byc ujemna lub rowna zero")
    }
    if(n==1)return listOf(0)
    if(n==2)return listOf(0,1)
    val smallerFibList = fibRec(n-1)
    val currentElem = smallerFibList[smallerFibList.size-1]+smallerFibList[smallerFibList.size-2]
    return smallerFibList + currentElem
}

fun collatz(c:Int):ArrayList<Int>{
    /**
     * Funkcja licze liczby z ciagu collatz dopóki nie wykryje 1 (potem wpadnie w cykl)
     * @param c pierwszy element ciagu ciągu
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy c jest liczbą ujemną
     * @return Funcja zwraca liste elementów ciągu collatz do wpadniecia przez niego w cykl
     * Korzystano z wzoru na ciąg collatz: https://pl.wikipedia.org/wiki/Problem_Collatza
     */
    if (c<=0){
        throw IllegalArgumentException("Wartosc c nie moze byc ujemna lub rowna zero")
    }
    var cycleFound = false
    var collatzList= ArrayList<Int>(listOf(c))
    var currentElem = c
    while (!cycleFound){
        if(currentElem%2==0){
            currentElem=currentElem/2
        }else{
            currentElem=3*currentElem+1
        }
        collatzList.add(currentElem)
        if(currentElem==1)cycleFound=true
    }
    return collatzList
}

fun komplement(strand:String):String{
    /**
     * Funkcja dla sekwencji nici kodującej DNA znajduje i zwraca sekwencję
     * nici matrycowej
     * @param strand typu String sekwencja nici kodującej DNA
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy nić zawiera inne litery niż A,T,C,G
     * @return Funcja zwraca sekwencję nici matrycowej
     * Korzystano z opisu z instrukcji
     */
    var result = ""
    for(s in strand){
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
fun transkrybuj(strand:String):String{
    /**
     * Funkcja dla sekwencji nici matrycowej DNA znajduje i zwraca sekwencję RNA
     * @param strand typu String sekwencja nici matrycowej DNA
     * @throws funkcja rzuca wyjątek IllegalArgumentException gdy nić zawiera inne litery niż A,T,C,G
     * @return Funcja zwraca sekwencję RNA
     * Korzystano z opisu z instrukcji
     */
    var result = ""
    for(s in strand){
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
    return result
}
fun main(){
    println("Zadanie 1: heron")
    println(heron(6.0,8.0,10.0))

    println("Zadanie 2: wspolne")
    val x= intArrayOf(1,2,2,3,3,3,6,7)
    val y= intArrayOf(1,1,2,3,4,6,7)

    val expectedCommon = ArrayList<Int>(listOf(1,2,3,6,7))
    val actualCommon = common(x,y)
    assertEquals(expectedCommon, actualCommon)
    println(common(x,y))

    println("Zadanie 3: podzbiory")
    val xSet = setOf(1,2,3)
    val expectedSubSets = setOf(setOf(), setOf(1), setOf(2), setOf(3), setOf(1,2), setOf(1,3), setOf(2,3), setOf(1,2,3))
    val actualSubSets = subSets(xSet)

    assertEquals(expectedSubSets, actualSubSets)
    println(subSets(xSet))

    println("Zadanie 4")
    println("iteracje")
    val n = 4

//    val expectedfibIter = ArrayList<Int>(listOf(0))//n=1
//    val expectedfibIter = ArrayList<Int>(listOf(0,1))//n=2
    val expectedfibIter = ArrayList<Int>(listOf(0,1,1,2))//n=4
    val actualfibIter = fibIter(n)
    assertEquals(expectedfibIter, actualfibIter)
    println(fibIter(n))

    println("rekurencja")
    val nRec = 4

    val expectedfibRec = ArrayList<Int>(listOf(0,1,1,2))//n=4
    val actualfibRec = fibRec(nRec)

    assertEquals(expectedfibRec, actualfibRec)
    println(fibRec(nRec))

    println("zadanie 5")
    println(collatz(11))
    val c0=11
    val expectedCollatz = ArrayList<Int>(listOf(11, 34, 17, 52, 26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1))//n=4
    val actualCollatz = collatz(c0)
    assertEquals(expectedCollatz, actualCollatz)
    println(collatz(c0))

    println("zadanie 6")
    println("komplement")
    println(komplement("AAGCTG"))
    val dnaStrand="AAGCTG"
    val expectedKomplement = "CAGCTT"
    val actualKomplement = komplement(dnaStrand)
    assertEquals(expectedKomplement, actualKomplement)


    println("transkrybuj")
    val dnaStrand2="TTCGAC"
    val expectedTranskrybuj = "AAGCUG"
    val actualTranskrybuj = transkrybuj(dnaStrand2)
    assertEquals(expectedTranskrybuj, actualTranskrybuj)
    println(transkrybuj(dnaStrand2))

}
