package chess;

public class Pawn extends ChessPiece {
    private int xst,yst;
    public Pawn(String s,int x, int y,Board b) {
    	super(s,b,"P",1);
    	this.xst=x;
    	this.yst=y;
    }
	@Override
	public boolean moveValid(int xs, int ys, int xe, int ye) {
		// TODO Auto-generated method stub
		if(xs==this.xst&&ys==this.yst) {
			//System.out.println("x start: "+xst+" y start: "+yst);
			if(xe==xs&&(ye==ys-1||ye==ys-2)&&this.getBoard().getChessPiece(xe,ye).noPiece()) {
				if(ye==ys-2) {
					if(!this.getBoard().getChessPiece(xe, ys-1).noPiece()) {
						return false;
					}
				}
				return true;
			}
			if((xe==xs+1||xe==xs-1)&&ye==ys-1&&!this.getBoard().getChessPiece(xe,ye).noPiece()&&!sameSide(this.getBoard().getChessPiece(xe,ye))) {return true;}
			//System.out.println(this.getBoard().getChessPiece(xe,ye).getName()+this.getBoard().getChessPiece(xe,ye).noPiece());
		}
		else {
			if(xe==xs&&ye==ys-1&&this.getBoard().getChessPiece(xe,ye).noPiece()) {return true;}
			if((xe==xs+1||xe==xs-1)&&ye==ys-1&&!this.getBoard().getChessPiece(xe,ye).noPiece()&&!sameSide(this.getBoard().getChessPiece(xe,ye))) {return true;}
		}
		
		return false;
	}
}
