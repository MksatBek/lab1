package ru.spbstu.telematics.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/*
Самым простым и понятным способом вычисления числа пи является численный метод Монте-Карло, суть которого сводится к простейшему перебору точек на площади.
Суть расчета заключается в том, что мы берем квадрат со стороной a = 2 R, вписываем в него круг радиусом R. И начинаем наугад ставить точки внутри квадрата.
 Геометрически, вероятность P1 того, чтот точка попадет в круг, равна отношению площадей круга и квадрата:
P1=Sкруг / Sквадрата = πR2 / a 2 = πR2 / (2 R ) 2= πR2 / (2 R) 2 = π / 4

Вероятность попадания точки в круг можно также посчитать после численного эксперимента ещё проще:
 посчитать количество точек, попавших в круг, и поделить их на общее количество поставленных точек:
P2=Nпопавших в круг / Nточек;
Так, при большом количестве точек в численном эксперименте вероятности должны вести себя cледующим образом:
lim(Nточек→∞)⁡(P2-P1)=0; (3)
Следовательно:
π / 4 = Nпопавших в круг / Nточек;
π =4 Nпопавших в круг / Nточек;

Программа принимает на вход количество создаваемых потоков и количество итераций.

 */






class MonteCarlo extends Thread
{

    int N; // количество итераций
    int inside; // количество попаданий в  окружность



    MonteCarlo(int CPUs, int N){
        this.N =N/CPUs;
    }



    Boolean isInCircle(double x, double y){ // попали в круг?
        return x*x+y*y<1.0;
    }


    @Override
    public void run()
    {
        Random r = new Random();
        for(int i=0;i<N;i++){
            if(isInCircle(r.nextDouble(), r.nextDouble())){
                inside++;
            }
        }
        return;
    }

}

class ThreadApp{


    double compute(int CPUs, int N){ //  CPUs количество ядер (и как следствие создаваемых потоков) N - количество итераций


        ArrayList<MonteCarlo> a = new ArrayList<MonteCarlo>();
        for(int i=0; i<CPUs;i++){
            a.add(new MonteCarlo(CPUs,N));
            a.get(i).start(); // запускаем потоки
        }

        //даем всем потокам возможность закончить выполнение перед тем, как программа (главный поток) закончит свое выполнение
        try {

            for(int i=0; i<CPUs;i++){
                a.get(i).join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int inside_sum=0; // считаем сумму
        for(int i=0; i<CPUs;i++){
            inside_sum = inside_sum + a.get(i).inside;
        }

        double pi  = 4.0*(inside_sum/ (double)(N));
        //System.out.println(4.0*(inside_sum/ (double)(N)));//   π =4 * Nпопавших в круг / Nточек (итераций)
        System.out.println("pi is "  + pi);
        return  pi;
    }

}








public class App
{

    public static void main(String[] args) {

        ThreadApp a = new ThreadApp();
        a.compute(3, 50000);

    }

}
