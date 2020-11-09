package javaconcurrency;

import java.util.List;
import java.util.stream.Collectors;

public class MyRunnable implements Runnable {

    private List<Integer> list;
    private int devider;

    public MyRunnable(List<Integer> list, int teiler) {
        this.list = list;
        this.devider = teiler;
    }

    public void run() {
        list = list.stream()
                .filter(s -> s % devider == 0)
                .collect(Collectors.toList());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
