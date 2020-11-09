package javaconcurrency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadsNumbers {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in, "Windows-1252");
        System.out.print("chunks> ");
        int chunks = s.nextInt();
        System.out.print("divider> ");
        int divider = s.nextInt();
        readFile();
        divideNumber(chunks, divider);
    }

    public static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("numbers.csv"));) {
            String line = br.readLine();
            while (line != null) {
                String[] val = line.split(":");

                if (line.equals("")) {
                    line = br.readLine();
                    continue;
                }

                for (int i = 0; i < val.length; i++) {
                    if (isNumeric(val[i])) {
                        list.add(Integer.parseInt(val[i]));
                    }
                }
                line = br.readLine();
            }
            //list.forEach(l -> System.out.println(l));

        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    public static void divideNumber(int chunks, int divider) {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(chunks);
        List<Integer> listSub = null;
        int deviderForArea = list.size() / chunks;
        for (int i = 0; i < chunks; i++) {
            listSub = list.subList(i * deviderForArea, i * deviderForArea + deviderForArea);
            MyRunnable run = new MyRunnable(listSub, divider);
            tpe.execute(run);
        }
        tpe.shutdown();
    }

    public static boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
