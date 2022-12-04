package demo.gb.lessons.counter;

public class LockTest {
    //множество потоков меняют значение в счетчике одновременно. В итоге должно получиться 0
    public LockTest(LockCounter counter){
        for (int i = 0; i<100; i++) {
            new Thread(()->{
                for(int j =0;j<=1000;j++) counter.increment();
            }).start();
            new Thread(()->{
                for(int j =0;j<=1000;j++) counter.decrement();
            }).start();
        }
        //ожидание вычислений
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //вывод значения счетчика после вычислений
        System.out.println(counter.getCount());
    }
}
