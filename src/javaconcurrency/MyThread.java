package javaconcurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer> {

    int untergrenze;
    int result = 0;
    List<Integer> list = new ArrayList<>();

    public MyThread(List<Integer> list, int untergrenze) {

        this.list = list;
        this.untergrenze = untergrenze;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }

}
