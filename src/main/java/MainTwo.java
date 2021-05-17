import java.util.concurrent.*;

public class MainTwo {
    public static void main(String[] args) throws Exception {
        final int timeCall = 1500;
        final int timeAnswer = 4000;
        final int callCount = 60;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        Thread callSubscriber = new Thread("Абонент") {
            public void run() {
                try {
                    for (int i = 0; i < callCount; i++) {
                        Thread.sleep(timeCall);
                        int nextInt = ThreadLocalRandom.current().nextInt(0, 550);
                        queue.put(nextInt);
                        System.out.printf("[%s] звонит, номер в очереди : %s %n", Thread
                                .currentThread().getName(), nextInt);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread operator1 = new Thread("Оператор 1") {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(timeAnswer);
                        Integer take = queue.take();
                        System.out.printf("[%s] отвечает абоненту: %s %n", Thread
                                .currentThread().getName(), take + "\n Приносим извинения за ожидания. Это займёт некоторое время.");
                        if (queue.isEmpty()){
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread operator2 = new Thread("Оператор 2") {
            public void run() {
                try {
                    while (true) {
                            Thread.sleep(timeAnswer);
                            Integer take = queue.take();
                            System.out.printf("[%s] отвечает абоненту: %s %n", Thread
                                    .currentThread().getName(), take + "\n Приносим извинения за ожидания. Это займёт некоторое время.");
                            if (queue.isEmpty()){
                                break;
                            }
                        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        callSubscriber.start();
        //operator1.start();
        operator2.start();
    }
}
