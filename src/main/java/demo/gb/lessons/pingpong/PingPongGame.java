package demo.gb.lessons.pingpong;

public class PingPongGame{

    public PingPongGame(){
        new PingPlayer(this);
        new PongPlayer(this);
    }

    public Boolean lastWasPing = false;

    synchronized void ping() {
        while (lastWasPing) {
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Ping "+Thread.currentThread().getName());
        lastWasPing = true;
        notify();
    }

     synchronized void pong() {
        while (!lastWasPing) {
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Pong "+Thread.currentThread().getName());
        lastWasPing = false;
        notify();
    }
}
