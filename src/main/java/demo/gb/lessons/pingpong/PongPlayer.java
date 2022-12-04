package demo.gb.lessons.pingpong;

public class PongPlayer implements Runnable{
    private PingPongGame game;

    public PongPlayer(PingPongGame game) {
        this.game = game;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) game.pong();
    }
}
