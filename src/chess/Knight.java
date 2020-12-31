package chess;

public class Knight extends ChessPiece {
    public Knight(String s, Board b) {
    	super(s,b,"KI",2);
    }
    public boolean moveValid(int xs, int ys, int xe, int ye) {
    	if(Math.abs(xe-xs)==2&&Math.abs(ye-ys)==1) {
		    if(!getBoard().getChessPiece(xe, ye).sameSide(this)) {return true;}
    	}
    	if(Math.abs(ye-ys)==2&&Math.abs(xe-xs)==1) {
    		if(!getBoard().getChessPiece(xe, ye).sameSide(this)) {return true;}
        }
    	return false;
    }
}
