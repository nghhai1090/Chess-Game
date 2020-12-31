package chess;

import java.awt.Point;
import java.util.ArrayList;

public class GamePlay {
	
	public void play(Player p1, Bot bot) {
		Board b= newBoard(p1.getColor(),bot.getColor(),true);
		Board c= newBoard(bot.getColor(),p1.getColor(),false);
		p1.setButton();
		p1.loadboard(b);
	    p1.loadIcon();
	    bot.loadboard(c);
	    ArrayList<Integer> moves=new ArrayList<Integer>();
	    int nummoves=0;
		while(!p1.lose()) {
			nummoves++;
			if(nummoves>=10) {if(p1.stalemate()) {break;}}
			p1.move();
		    boolean notmove=p1.notMove();
			while(notmove) {System.out.print("");notmove=p1.notMove();}
            p1.pause();
            moves= p1.getMoves();
			//System.out.println("white moves"+moves.get(0)+", "+moves.get(1)+" to "+moves.get(2)+", "+moves.get(3));
			if(moves.size()==4) {bot.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));}
			else if(moves.size()==5) {bot.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3),moves.get(4));}
			else {
				bot.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));
				bot.movePiece(7-moves.get(4), 7-moves.get(5), 7-moves.get(6), 7-moves.get(7));
			}
			if(bot.lose()) {break;}
			if(nummoves>=10) {if(bot.stalemate()) {break;}}
	        bot.move();
			moves= bot.getMoves();
			//System.out.println("black moves"+moves.get(0)+", "+moves.get(1)+" to "+moves.get(2)+", "+moves.get(3));
			if(moves.size()==4) {p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));}
			else if(moves.size()==5) {p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3),moves.get(4));}
			else {
				p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));
				p1.movePiece(7-moves.get(4), 7-moves.get(5), 7-moves.get(6), 7-moves.get(7));
			}
		}
		if(p1.lose()) {p1.announce(bot.getColor());}
		else if (bot.lose()){p1.announce(p1.getColor());}
		else {p1.draw();}
	}
	
	public void play(Player p1,Player p2) {
		Board b= newBoard(p1.getColor(),p2.getColor(),true);
		Board c= newBoard(p2.getColor(),p1.getColor(),false);
		p1.setButton();
		p2.setButton();
		p2.loadboard(c);
        p2.loadIcon();
        p1.loadboard(b);
        p1.loadIcon();
        ArrayList<Integer> moves=new ArrayList<Integer>();
        int nummoves=0;
		while(!p1.lose()) {
			nummoves++;
			if(nummoves>=10) {if(p1.stalemate()) {break;}}
			p1.move();
		    boolean notmove=p1.notMove();
			while(notmove) {System.out.print("");notmove=p1.notMove();}
            p1.pause();
            moves= p1.getMoves();
			//System.out.println("white moves"+moves.get(0)+", "+moves.get(1)+" to "+moves.get(2)+", "+moves.get(3));
			if(moves.size()==4) {p2.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));}
			else if(moves.size()==5) {p2.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3),moves.get(4));}
			else {
				p2.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));
				p2.movePiece(7-moves.get(4), 7-moves.get(5), 7-moves.get(6), 7-moves.get(7));
			}
			if(p2.lose()) {break;}
			if(nummoves>=10) {if(p2.stalemate()) {break;}}
	        p2.move();
	        notmove=p2.notMove();
			while(notmove) {System.out.print("");notmove=p2.notMove();}
			p2.pause();
			moves= p2.getMoves();
			//System.out.println("black moves"+moves.get(0)+", "+moves.get(1)+" to "+moves.get(2)+", "+moves.get(3));
			if(moves.size()==4) {p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));}
			else if(moves.size()==5) {p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3),moves.get(4));}
			else {
				p1.movePiece(7-moves.get(0), 7-moves.get(1), 7-moves.get(2), 7-moves.get(3));
				p1.movePiece(7-moves.get(4), 7-moves.get(5), 7-moves.get(6), 7-moves.get(7));
			}
		}
		if(p1.lose()) {p1.announce(p2.getColor());p2.announce(p2.getColor());}
		else if (p2.lose()){p1.announce(p1.getColor());p2.announce(p1.getColor());}
		else {p1.draw();p2.draw();}
	}
	public void printboard(Board b) {
		ChessPiece[][] board = b.getBoard();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(b.getChessPiece(i, j).getName()+b.getChessPiece(i, j).getColor()+" | ");	
			}
			System.out.println();
		}
	}
	public Board newBoard(String c1, String c2,boolean first) {
		ChessPiece[][] b = new ChessPiece[8][8];	
		Point k=new Point(3,7);
		if(first) {
	        k =new Point(4,7);
		}
		Board board = new Board(b,true,true, k);
		for(int i=0; i<8;i++) {
		    for(int j=0; j<8; j++) {
		    	board.addPiece(new ChessPiece(board), i, j);
		    }
		}
		board.addPiece(new Castle(c2,board,"l"),0, 0);
		board.addPiece(new Castle(c2,board,"r"),7, 0);
		board.addPiece(new Castle(c1,board,"r"),7, 7);
		board.addPiece(new Castle(c1,board,"l"),0, 7);
		board.addPiece(new Knight(c2,board),1, 0);
		board.addPiece(new Knight(c2,board),6, 0);
		board.addPiece(new Knight(c1,board),6, 7);
		board.addPiece(new Knight(c1,board),1, 7);
		board.addPiece(new Bishop(c2,board),2, 0);
		board.addPiece(new Bishop(c2,board),5, 0);
		board.addPiece(new Bishop(c1,board),5, 7);
		board.addPiece(new Bishop(c1,board),2, 7);
		if(first) {board.addPiece(new Queen(c1,board), 3, 7);board.addPiece(new Queen(c2,board), 3, 0);}
		else {board.addPiece(new Queen(c1,board), 4, 7);board.addPiece(new Queen(c2,board), 4, 0);}
		if(first) {board.addPiece(new King(c1,board),4, 7);board.addPiece(new King(c2,board),4, 0);}
		else {board.addPiece(new King(c1,board),3, 7);board.addPiece(new King(c2,board),3, 0);}
		for(int i=0; i<8; i++) {
			board.addPiece(new Pawn(c2,7-i,6,board), i, 1);
		}
		for(int i=0; i<8; i++) {
			board.addPiece(new Pawn(c1,i,6,board), i, 6);
		}
		return board;
	}
}
