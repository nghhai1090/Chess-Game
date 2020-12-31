package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Bot  {
    private String color;
    private String ecolor;
    private Board b;
    private ArrayList<Integer>moves;
    public Bot(String c,String e) {
    	color=c;
    	ecolor=e;
    	moves=new ArrayList<Integer>();
    }
    public void loadboard(Board b) {this.b=b;}
    public String getColor() {return color;} 
    public void move(){
    	moves.clear();
    	ArrayList<Integer> m= new ArrayList<Integer>();
    	ArrayList<Integer> p= new ArrayList<Integer>();
    	for(int i=0; i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if(b.getChessPiece(i, j).getColor().contentEquals(getColor())) {
    			    for(int x=0;x<8;x++) {
    			    	for(int y=0;y<8;y++) {
    			    		if(b.getChessPiece(i, j).moveValid(i, j, x, y)) {
    			    			ChessPiece p1= b.getChessPiece(i, j);
    			    			ChessPiece p2= b.getChessPiece(x, y);
	                            b.addPiece(new ChessPiece(b), i, j);
	                    		b.addPiece(p1,x ,y);
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(x, y);}
	                    		if(!b.checkKing(b.posK1(), getColor())) {
	                    			p.add(p2.value());
	                    			m.add(i);m.add(j);m.add(x);m.add(y);
	                    		}	
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(i, j);}
	                    		b.addPiece(p1, i, j);
	                    		b.addPiece(p2, x, y);
    			    		}
    			    	}
    			    }	
    			}		
    		}
    	}
    	int max = Collections.max(p);
    	System.out.println(max);
    	ArrayList<Integer> maxlist=new ArrayList<Integer>();
    	for(int i=0;i<p.size();i++) {
    		if(p.get(i)==max) {
    			maxlist.add(i);
    		}
    	}
    	int mindex=0;
    	System.out.println(maxlist.size());
    	if(maxlist.size()>1){ mindex=  new Random().nextInt(maxlist.size()-1-0) + 0;}
    	int index= maxlist.get(mindex);
    	boolean queen=false;
    	if(b.getChessPiece(m.get(4*index),m.get(4*index+1)).getName().equals("K")) {b.updateK1(m.get(4*index+2),m.get(4*index+3));}
    	else if(b.getChessPiece(m.get(4*index),m.get(4*index+1)).getName().equals("P")) {
    		if(m.get(4*index+1)==1&&m.get(4*index+3)==0) {
    			b.addPiece(new Queen(getColor(),b), m.get(4*index),m.get(4*index+1));
    			queen=true;
    		}
    	}
    	movePiece(m.get(4*index),m.get(4*index+1),m.get(4*index+2),m.get(4*index+3));
    	moves.add(m.get(4*index));moves.add(m.get(4*index+1));moves.add(m.get(4*index+2));moves.add(m.get(4*index+3));
    	if(queen==true) {moves.add(1);}
    }
	public ArrayList<Integer> getMoves(){return moves;}
	public boolean stalemate() {
    	int count =0;
    	for(int i=0; i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if(b.getChessPiece(i, j).getColor().contentEquals(getColor())) {
    			    for(int x=0;x<8;x++) {
    			    	for(int y=0;y<8;y++) {
    			    		if(b.getChessPiece(i, j).moveValid(i, j, x, y)) {
    			    			ChessPiece p1= b.getChessPiece(i, j);
    			    			ChessPiece p2= b.getChessPiece(x, y);
	                            b.addPiece(new ChessPiece(b), i, j);
	                    		b.addPiece(p1,x ,y);
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(x, y);}
	                    		if(!b.checkKing(b.posK1(), getColor())) {
	                    		    count++;
	                    		}	
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(i, j);}
	                    	    b.addPiece(p1, i, j);
                    		    b.addPiece(p2, x, y);
    			    		}
    			    	}
    			    }	
    			}		
    		}
    	}
    	System.out.println(getColor()+" check stalemate "+count);
    	return count==0;
    }
	public boolean lose() {
    	int count =0;
    	for(int i=0; i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if(b.getChessPiece(i, j).getColor().contentEquals(getColor())) {
    			    for(int x=0;x<8;x++) {
    			    	for(int y=0;y<8;y++) {
    			    		if(b.getChessPiece(i, j).moveValid(i, j, x, y)) {
    			    			ChessPiece p1= b.getChessPiece(i, j);
    			    			ChessPiece p2= b.getChessPiece(x, y);
	                            b.addPiece(new ChessPiece(b), i, j);
	                    		b.addPiece(p1,x ,y);
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(x, y);}
	                    		if(!b.checkKing(b.posK1(), getColor())) {
	                    		    count++;
	                    		}	
	                    		if(p1.getName().contentEquals("K")) {b.updateK1(i, j);}
	                    	    b.addPiece(p1, i, j);
                    		    b.addPiece(p2, x, y);
    			    		}
    			    	}
    			    }	
    			}		
    		}
    	}
    	System.out.println(getColor()+" check lose "+count);
    	return b.checkKing(b.posK1(), getColor())&&count==0;
    }
	public void movePiece(int xs, int ys, int xe, int ye) {
    	b.movePiece(xs, ys, xe, ye);
    }
    public void movePiece(int xs, int ys, int xe, int ye,int c) {
    	if(c==1) {b.addPiece(new Queen(ecolor,b), xs, ys);}
    	else if(c==2) {b.addPiece(new Knight(ecolor,b), xs, ys);}
    	else if(c==3) {b.addPiece(new Bishop(ecolor,b), xs, ys);}
    	else {b.addPiece(new Castle(ecolor,b,"f"), xs, ys);}
    	b.movePiece(xs, ys, xe, ye);
    }
}
