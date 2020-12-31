package chess;

import javax.swing.ImageIcon;

public class ChessPiece {
    private String color;
    private String name;
    private ImageIcon icon;
    private Board b;
    private int value ;
    public ChessPiece(Board b) {
    	color="";
    	name="no";
    	icon=null;
    	b=b;
    	value=0;
    }
    public ChessPiece(String s,Board b,String q,int value) {
    	this.color=s;
    	this.b=b;
    	this.name=q;
    	this.value=value;
    	this.icon=new ImageIcon(getClass().getClassLoader().getResource(q+s+".png"));
    }
    public boolean moveValid(int xs, int ys, int xe, int ye) {return false;}
	public ImageIcon getIcon() {return this.icon;}
	public String getColor() {return color;}
	public String getName() {return name;}
	public Board getBoard() {return b;}
	public void setBoard(Board b) {this.b=b;}
	public boolean sameSide(ChessPiece p) {return this.getColor().equals(p.getColor());}
	public boolean noPiece() {return name.equals("no");}
	public String getSide() {return "";}
	public int value() {return value;}
}
