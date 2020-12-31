package chess;

public class Castle extends ChessPiece {
	private String lf;
    public Castle(String s, Board b,String lf) {
    	super(s,b,"C",4);
    	this.lf=lf;
    }
    public String getSide() {return lf;}
    public boolean moveValid(int xs, int ys, int xe, int ye) {
    	if(xe!=xs&&ye!=ys) {return false;}
    	if(xe==xs) {
    		if(ye>ys) {
    			for(int i= ys+1; i<ye; i++) {
    				if(!getBoard().getChessPiece(xe, i).noPiece()) {return false;}
    			}
    		}
    		if(ye<ys) {
    			for(int i= ys-1; i>ye; i--) {
    				if(!getBoard().getChessPiece(xe, i).noPiece()) {return false;}
    			}
    		}
    	}
    	else if(ye==ys) {
    		if(xe>xs) {
    			for(int i= xs+1; i<xe; i++) {
    				if(!getBoard().getChessPiece(i, ye).noPiece()) {return false;}
    			}
    		}
    		if(xe<xs) {
    			for(int i= xs-1; i>xe; i--) {
    				if(!getBoard().getChessPiece(i, ye).noPiece()) {return false;}
    			}
    		}
    	}
    	if(getBoard().getChessPiece(xe, ye).sameSide(this)) {return false;}
    	return true;
    }
}
