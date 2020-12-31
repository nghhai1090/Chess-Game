package chess;

public class Bishop extends ChessPiece {
    public Bishop(String s, Board b) {
    	super(s,b,"B",3);
    }
    public boolean moveValid(int xs, int ys ,int xe, int ye) {
    	if(Math.abs(ys-ye)==Math.abs(xs-xe)) {
    		if((ye-ys)==(xe-xs)&&xe>xs&&ye>ys) {
    			int plus=1;
    			while(xs+plus!=xe) {
    				if(!getBoard().getChessPiece(xs+plus, ys+plus).noPiece()) {return false;}
    				plus++;
    			}
    			if(getBoard().getChessPiece(xe,ye).sameSide(this)) {return false;}
    			return true;
    		}
    		else if((ye-ys)==(xe-xs)&&xe<xs&&ye<ys) {
    			int plus=1;
    			while(xs-plus!=xe) {
    				if(!getBoard().getChessPiece(xs-plus, ys-plus).noPiece()) {return false;}
    				plus++;
    			}
    			if(getBoard().getChessPiece(xe,ye).sameSide(this)) {return false;}
    			return true;
    		}
    		else if((ye-ys)==-(xe-xs)&&xe<xs&&ye>ys) {
    			int plus=1;
    			while(xs-plus!=xe) {
    				if(!getBoard().getChessPiece(xs-plus, ys+plus).noPiece()) {return false;}
    				plus++;
    			}
    			if(getBoard().getChessPiece(xe,ye).sameSide(this)) {return false;}
    			return true;
    		}
    		else if((ye-ys)==-(xe-xs)&&xe>xs&&ye<ys) {
    			int plus=1;
    			while(xs+plus!=xe) {
    				if(!getBoard().getChessPiece(xs+plus, ys-plus).noPiece()) {return false;}
    				plus++;
    			}
    			if(getBoard().getChessPiece(xe,ye).sameSide(this)) {return false;}
    			return true;
    		}
    	}
    	return false;
    }
}
