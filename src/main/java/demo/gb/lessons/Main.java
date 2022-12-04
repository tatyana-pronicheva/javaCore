package demo.gb.lessons;

import demo.gb.lessons.counter.LockCounter;
import demo.gb.lessons.counter.LockTest;
import demo.gb.lessons.pingpong.PingPongGame;

public class Main {
    public static void main(String[] args){
       // new PingPongGame();
        new LockTest(new LockCounter());
    }
}
