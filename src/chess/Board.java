package chess;

import java.awt.Point;

public class Board {
	private ChessPiece[][] board;
	private boolean castle1;
	private boolean castle2;
	private Point king1;//position my king
    
	public Board(ChessPiece[][]b, boolean c1,boolean c2, Point k1) {this.board=b;castle1=c1;castle2=c2;king1=k1;}
    public ChessPiece getChessPiece(int x, int y) {return board[y][x];}
	public ChessPiece[][] getBoard(){return board;}
	public void addPiece(ChessPiece p, int x , int y) {board[y][x]=p;p.setBoard(this);}
	public ChessPiece movePiece(int xs, int ys,int xe, int ye) {
		ChessPiece killed=null;
		if(xs!=xe||ys!=ye) {
		    killed = board[ye][xe];
		    board[ye][xe]=board[ys][xs]; 
	        board[ys][xs]=new ChessPiece(this);
	    }
	    return killed;
	} 
	public boolean getCastle1() {return castle1;}
	public void setCastle1(boolean b) {castle1=b;}
	public boolean getCastle2() {return castle2;}
	public void setCastle2(boolean b) {castle2=b;}
	public void updateK1(int x , int y) {Point k= new Point(x,y);king1=k;}
	public Point posK1() {return king1;}
	public boolean checkKing(Point k,String s) {
		int kx=(int) k.getX();
		int ky=(int) k.getY();
	    for(int i=kx+1; i< board.length;i++) {// ngang phai
	    	if(!this.board[ky][i].noPiece()) {
	    		if(i==kx+1) {if(!this.board[ky][i].sameSide(new King(s,this))&&this.board[ky][i].getName().equals("K")) {return true;}}
	    		if(!this.board[ky][i].sameSide(new King(s,this))&&(this.board[ky][i].getName().equals("C")||this.board[ky][i].getName().equals("Q"))){return true;}
	    		else {break;}
	    	}
	    }
	    for(int i=kx-1; i>= 0;i--) {// ngang trai
	    	if(!this.board[ky][i].noPiece()) {
	    		if(i==kx-1) {if(!this.board[ky][i].sameSide(new King(s,this))&&this.board[ky][i].getName().equals("K")) {return true;}}
	    		if(!this.board[ky][i].sameSide(new King(s,this))&&(this.board[ky][i].getName().equals("C")||this.board[ky][i].getName().equals("Q"))){return true;}
	    		else {break;}
	    	}
	    }
	    for(int i=ky+1; i< board[kx].length;i++) {//truoc
	    	if(!this.board[i][kx].noPiece()) {
	    		if(i==ky+1) {if(!this.board[i][kx].sameSide(new King(s,this))&&this.board[i][kx].getName().equals("K")) {return true;}}
	    		if(!this.board[i][kx].sameSide(new King(s,this))&&(this.board[i][kx].getName().equals("C")||this.board[i][kx].getName().equals("Q"))){return true;}
	    		else {break;}
	    	}
	    }
	    for(int i=ky-1; i>= 0;i--) {//sau
	    	if(!this.board[i][kx].noPiece()) {
	    		if(i==ky-1) {if(!this.board[i][kx].sameSide(new King(s,this))&&this.board[i][kx].getName().equals("K")) {return true;}}
	    		if(!this.board[i][kx].sameSide(new King(s,this))&&(this.board[i][kx].getName().equals("C")||this.board[i][kx].getName().equals("Q"))){return true;}
	    		else {break;}
	    	}
	    }
	    int plus=1;
		while(kx+plus<board.length&&ky-plus>=0)//cheo phai tren
		{
			if(!board[ky-plus][kx+plus].noPiece()) {
				if(plus==1) {if(!new King(s,this).sameSide(board[ky-plus][kx+plus])&&(this.board[ky-plus][kx+plus].getName().equals("P")||this.board[ky-plus][kx+plus].getName().equals("K"))){return true;}}
				if(!new King(s,this).sameSide(board[ky-plus][kx+plus])&&(this.board[ky-plus][kx+plus].getName().equals("B")||this.board[ky-plus][kx+plus].getName().equals("Q"))) {return true;}
				else {break;}
			}
			plus++;
		}
		plus=1;
		while(kx+plus<board.length&&ky+plus<board[kx].length)//cheo phai duoi
		{
			if(!board[ky+plus][kx+plus].noPiece()) {
				if(plus==1) {if(!new King(s,this).sameSide(board[ky+plus][kx+plus])&&this.board[ky+plus][kx+plus].getName().equals("K")) {return true;}}
				if(!new King(s,this).sameSide(board[ky+plus][kx+plus])&&(this.board[ky+plus][kx+plus].getName().equals("B")||this.board[ky+plus][kx+plus].getName().equals("Q"))) {return true;}
				else {break;}
			}
			plus++;
		}
		plus=1;
		while(kx-plus>=0&&ky-plus>=0)//cheo trai tren
		{
			if(!board[ky-plus][kx-plus].noPiece() ){
				if(plus==1) {if(!new King(s,this).sameSide(board[ky-plus][kx-plus])&&(this.board[ky-plus][kx-plus].getName().equals("P")||this.board[ky-plus][kx-plus].getName().equals("K"))){return true;}}
				if(!new King(s,this).sameSide(board[ky-plus][kx-plus])&&(this.board[ky-plus][kx-plus].getName().equals("B")||this.board[ky-plus][kx-plus].getName().equals("Q"))) {return true;}
				else {break;}
			}
			plus++;
		}
		plus=1;
		while(kx-plus>=0&&ky+plus<board[kx].length)//cheo trai duoi
		{
			if(!board[ky+plus][kx-plus].noPiece()) {
				if(plus==1) {if(!new King(s,this).sameSide(board[ky+plus][kx-plus])&&this.board[ky+plus][kx-plus].getName().equals("K")) {return true;}}
				if(!new King(s,this).sameSide(board[ky+plus][kx-plus])&&(this.board[ky+plus][kx-plus].getName().equals("B")||this.board[ky+plus][kx-plus].getName().equals("Q"))) {return true;}
				else {break;}
			}
			plus++;
		}
		if(kx-2>=0&&ky-1>=0) {
			if(!board[ky-1][kx-2].noPiece()&&!board[ky-1][kx-2].sameSide(new King(s,this))&&board[ky-1][kx-2].getName().equals("KI")) {return true;}
		}
		if(kx-2>=0&&ky+1<board[kx].length) {
			if(!board[ky+1][kx-2].noPiece()&&!board[ky+1][kx-2].sameSide(new King(s,this))&&board[ky+1][kx-2].getName().equals("KI")) {return true;}
		}
		if(kx+2<board.length&&ky-1>=0) {
			if(!board[ky-1][kx+2].noPiece()&&!board[ky-1][kx+2].sameSide(new King(s,this))&&board[ky-1][kx+2].getName().equals("KI")) {return true;}
		}
		if(kx+2<board.length&&ky+1<board[kx].length) {
			if(!board[ky+1][kx+2].noPiece()&&!board[ky+1][kx+2].sameSide(new King(s,this))&&board[ky+1][kx+2].getName().equals("KI")) {return true;}
		}
		if(ky-2>=0&&kx-1>=0) {
			if(!board[ky-2][kx-1].noPiece()&&!board[ky-2][kx-1].sameSide(new King(s,this))&&board[ky-2][kx-1].getName().equals("KI")) {return true;}
		}
		if(ky-2>=0&&kx+1<board[kx].length) {
			if(!board[ky-2][kx+1].noPiece()&&!board[ky-2][kx+1].sameSide(new King(s,this))&&board[ky-2][kx+1].getName().equals("KI")) {return true;}
		}
		if(ky+2<board.length&&kx-1>=0) {
			if(!board[ky+2][kx-1].noPiece()&&!board[ky+2][kx-1].sameSide(new King(s,this))&&board[ky+2][kx-1].getName().equals("KI")) {return true;}
		}
		if(ky+2<board.length&&kx+1<board[kx].length) {
			if(!board[ky+2][kx+1].noPiece()&&!board[ky+2][kx+1].sameSide(new King(s,this))&&board[ky+2][kx+1].getName().equals("KI")) {return true;}
		}
		
		return false;
	}
	public boolean checkmate(Point k,String s) {//wrong rule
		if(checkKing(k,s)) {
			int kx= (int) k.getX();
			int ky= (int)k.getY();
			for(int i=-1; i<=1; i++) {
				for(int j=-1;j<=1; j++) {
					if(kx+i<board.length&&kx+1>=0&&ky+j<board[kx].length&&ky+j>=0) {if(!getChessPiece(kx+i,ky+j).sameSide(getChessPiece(kx,ky))&&!checkKing(new Point(kx+i,ky+j),s)) {return false;}}
				}
			}
			return true;
		}
		return false;
	}
}