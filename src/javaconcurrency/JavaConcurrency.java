package javaconcurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaConcurrency {

    static int obergrenze = 0;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in, "Windows-1252");
        System.out.println("n> ");
        obergrenze = s.nextInt();
        System.out.print("Sum: ");
        divideNumber();
    }

    public static void divideNumber() {
        int teilObergrenze = 100;
        int untergrenze = 1;
        int chunks = (int) (obergrenze / 100 + 1);
        List<Integer> sublist = null;
        ExecutorService es = Executors.newFixedThreadPool(chunks);
        List<Future> is = new ArrayList<>();
        int sum = 0;

        for (int i = untergrenze; i <= obergrenze; i++) {
            list.add(i);
        }

        for (int i = 0; i < chunks; i++) {
            if (teilObergrenze > obergrenze) {
                teilObergrenze = obergrenze;
            }

            sublist = list.subList(untergrenze - 1, teilObergrenze);
            MyThread call = new MyThread(sublist, untergrenze);
            is.add(es.submit(call));
            untergrenze += 100;
            teilObergrenze += 100;
        }

        for (Future i : is) {
            try {
                sum += (int) i.get();
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            } catch (ExecutionException ex) {
                System.out.println("ExecutionException");
            }
        }
        es.shutdown();
        System.out.println(sum);
    }

}
