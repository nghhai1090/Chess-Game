package chess;

import java.awt.Point;

public class King extends ChessPiece {
    public King(String s,Board b) {
       super(s,b,"K",999);
    }
	@Override
	public boolean moveValid(int xs, int ys, int xe, int ye) {
		// TODO Auto-generated method stub
		if((Math.abs(xe-xs)==0&&Math.abs(ye-ys)==1)||(Math.abs(ye-ys)==0&&Math.abs(xe-xs)==1)||(Math.abs(ye-ys)==1&&Math.abs(xe-xs)==1)) {
			if(!sameSide(getBoard().getChessPiece(xe, ye))&&!getBoard().checkKing(new Point(xe,ye),getColor())) {
				return true;
			}
		}
		return false;
	}
}
