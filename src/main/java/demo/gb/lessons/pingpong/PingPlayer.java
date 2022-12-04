package demo.gb.lessons.pingpong;

public class PingPlayer implements Runnable{
    private PingPongGame game;

    public PingPlayer(PingPongGame game) {
        this.game = game;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) game.ping();
    }
}