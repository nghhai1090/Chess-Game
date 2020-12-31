package chess;

public class Queen extends ChessPiece {
    public Queen(String s, Board b) {
    	super(s,b,"Q",5);
    }
    public boolean moveValid(int xs, int ys,int xe, int ye) {
		Castle c = new Castle(getColor(),getBoard(),"l");
		Bishop b = new Bishop(getColor(),getBoard());
    	return c.moveValid(xs, ys, xe, ye)||b.moveValid(xs, ys, xe, ye);
    }
}
