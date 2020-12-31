package chess;

import java.awt.Point;

public class Test {
    public static void main(String[]args) {
    GamePlay g= new GamePlay();
    //Board b = g.newBoard("BL", "R");
    Player p1 =new Player("W","B");
    //Player p2 =new Player("B","W");
    Bot pb= new Bot("B","W");
    g.play(p1, pb);
    }
}
