package com.example.lista1_jezyki

import java.lang.Math.pow

class Wielomian{

    var wspolczynniki: IntArray = intArrayOf()

    constructor(wspolczynniki: IntArray){
        if (wspolczynniki.size > 0){
            if (wspolczynniki.size > 1 && wspolczynniki[0]==0){
                throw IllegalArgumentException("Współczynnik przy najwyższej potędze wielomianu nie moze byc zero")
            }
            this.wspolczynniki = wspolczynniki
        }else{
            throw IllegalArgumentException("Podano pustą liste współczynników, należy podać przynajmniej 1")
        }
    }
    fun stopien():Int{
        return wspolczynniki.size-1
    }
    fun tekstWersjaWielomianu():String{
        var tekstowaWersja = "W(x) ="
        var stopien = wspolczynniki.size-1
        wspolczynniki.forEachIndexed { index, it ->
            if(wspolczynniki.size==1 || it != 0){//jezeli jest tylko jeden element to go drukujrmy nawet jezeli to zero, a jezeli to zero a jest wiecej elementow, to nie drukujemy
                if (index!= 0){//dla pierwszedo elementu nie drukowany jest plus
                    tekstowaWersja+=" + "
                }
                if (stopien==0){
                    tekstowaWersja+="$it"
                }else{
                    if (stopien==1){
                        tekstowaWersja+="$it * x"
                    }else{
                        tekstowaWersja+="$it * (x^${stopien})"
                    }
                }
            }
            stopien-=1
        }
        return tekstowaWersja
    }

    fun obliczWartosc(x:Double):Double {
        var wartosc = 0.0
        var stopien = (wspolczynniki.size-1).toDouble()
        wspolczynniki.forEachIndexed { index, it ->
            wartosc+=it*pow(x,stopien)
            stopien-=1
        }
        return wartosc
    }
    operator fun invoke(x:Double): Double {
        //użyto: https://medium.com/@appdevinsights/concept-of-operator-overloading-in-kotlin-e190b86b00b5
        return obliczWartosc(x)
    }
    operator fun plus(w2: Wielomian): Wielomian {
        val noweWspolczynniki = mutableListOf<Int>()
        var i = wspolczynniki.size-1
        var j = w2.wspolczynniki.size-1
        while(i>=0 && j>=0){
            noweWspolczynniki.add(wspolczynniki[i]+w2.wspolczynniki[j])
            i-=1
            j-=1
        }
        if (i>=0){
            while(i>=0){
                noweWspolczynniki.add(wspolczynniki[i])
                i-=1
            }
        }else{
            if (j>=0){
                while(j>=0){
                    noweWspolczynniki.add(w2.wspolczynniki[j])
                    j-=1
                }
            }
        }
        noweWspolczynniki.reversed()
        while(noweWspolczynniki[0]==0 && noweWspolczynniki.size>1){
            noweWspolczynniki.removeAt(0)
        }
        return Wielomian(noweWspolczynniki.toIntArray())
    }
    operator fun minus(w2: Wielomian): Wielomian {
        val noweWspolczynniki = mutableListOf<Int>()
        var i = wspolczynniki.size-1
        var j = w2.wspolczynniki.size-1
        while(i>=0 && j>=0){
            noweWspolczynniki.add(wspolczynniki[i]-w2.wspolczynniki[j])
            i-=1
            j-=1
        }
        if (i>=0){
            while(i>=0){
                noweWspolczynniki.add(wspolczynniki[i])
                i-=1
            }
        }else{
            if (j>=0){
                while(j>=0){
                    noweWspolczynniki.add(-1 * w2.wspolczynniki[j])
                    j-=1
                }
            }
        }

        noweWspolczynniki.reverse()
        while(noweWspolczynniki[0]==0 && noweWspolczynniki.size>1){
            noweWspolczynniki.removeAt(0)
        }
        return Wielomian(noweWspolczynniki.toIntArray())
    }
    operator fun times(w2: Wielomian): Wielomian {
        val stopienWynikowegoWielomianu = stopien()+w2.stopien()
        val noweWspolczynniki = IntArray(stopienWynikowegoWielomianu+1){ 0 }
        var stopienIt = stopien()
        wspolczynniki.forEachIndexed { index, it ->
            var stopienItW2 = w2.stopien()
            w2.wspolczynniki.forEachIndexed { indexW2, itW2 ->
                noweWspolczynniki[stopienIt+stopienItW2] += it*itW2
                stopienItW2-=1
            }
            stopienIt-=1
        }

        noweWspolczynniki.reversed()
        var startIndeks=0
        while(noweWspolczynniki[startIndeks]==0 && noweWspolczynniki.size>1){
            startIndeks+=1
        }
        return Wielomian(noweWspolczynniki.sliceArray(indices = IntRange(startIndeks, noweWspolczynniki.size-1)))
    }
    operator fun plusAssign(w2: Wielomian){
        this.wspolczynniki = (this + w2).wspolczynniki
    }
    operator fun minusAssign(w2: Wielomian){
        this.wspolczynniki = (this - w2).wspolczynniki
    }
    operator fun timesAssign(w2: Wielomian){
        println(tekstWersjaWielomianu())
        println(w2.tekstWersjaWielomianu())
        this.wspolczynniki = (this - w2).wspolczynniki
    }
}

fun main(){
    val wspolczynniki = intArrayOf(1, 2, 3, 4)
    val w = Wielomian(wspolczynniki)
    println("Stopien: ${w.stopien()}")
    println(w.tekstWersjaWielomianu())
    println("Wartosc W(2): ${w.obliczWartosc(2.0)}")
    println("Wartosc W(2) by invoke operator: ${w(2.0)}")

    val w2 = Wielomian(intArrayOf( 1, 2, 3))
    val w3 = Wielomian(intArrayOf(1, -4))
    val w4 = w2 + w3
    println("Dodawanie wynik: ${w4.tekstWersjaWielomianu()}")
    val w5 = w2 - w3
    println(w5.tekstWersjaWielomianu())
    println("Odejmowanie wynik: ${w5.tekstWersjaWielomianu()}")
    val w6 = w2 * w3
    println("Mnozenie wynik: ${w6.tekstWersjaWielomianu()}")//W(x) =1 * (x^3) + -2 * (x^2) + -5 * x + -12
    w3 += w2
    println("Operator += wynik: ${w3.tekstWersjaWielomianu()}")
    w3 -= w2
    println("Operator -= wynik: ${w3.tekstWersjaWielomianu()}")
    w3 *= w2
    println("Operator *= wynik: ${w3.tekstWersjaWielomianu()}")
    
}