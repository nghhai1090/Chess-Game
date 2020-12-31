package chess;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Player extends JFrame {
    private String color;
    private String ecolor;
    private Board b;
    private boolean moved;
    private boolean clicked;
    private boolean turn;
    private JButton[][] buttons=new JButton[8][8];
    private ImageIcon playboard=new ImageIcon(getClass().getClassLoader().getResource("ban co vua.png"));
    private ChessPiece choosen;
    private ImageIcon icon;
    private Point save;
    private PlayPanel buttonpanel= new PlayPanel();
	private ArrayList<Integer> moves;
    
	public Player ( String color,String ecolor) {
    	this.color=color;
    	this.ecolor=ecolor;
    	this.moved=false;
    	this.clicked=false;
    	this.moves=new ArrayList<Integer>();
    	buttonpanel.setLayout(new GridLayout(8,8));
    	this.add(buttonpanel);
    	setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setVisible(true);
    }
    public void setButton() {
    	for(int i=0;i<8;i++) {
    		for(int j=0; j<8; j++) {
    		    buttons[i][j]=new JButton();
    		    buttons[i][j].setActionCommand(j+","+i);
    			buttons[i][j].setOpaque(false);
    			buttons[i][j].setContentAreaFilled(false);
    			buttons[i][j].addActionListener(new ActionListener() {
 	        	    public void actionPerformed(ActionEvent arg0) {
 	        	        String pos= arg0.getActionCommand();
 	                    int i= Character.getNumericValue(pos.charAt(0));
 	                    int j= Character.getNumericValue(pos.charAt(2)); 	   
 	                    if(turn==true) {
 	                        if(clicked==false) {
 	                            if(b.getChessPiece(i,j).getColor().equals(getColor())) {
 	                    	       // System.out.println("not click:"+clicked);
 	                    		    choosen=b.getChessPiece(i,j);
 	                    		    icon=b.getChessPiece(i, j).getIcon();
 	                    		    save= new Point(i,j);
 	                    		    //System.out.println("xs:"+i+" ys :"+j );
 	                    		    //System.out.println("name to move :"+choosen.getName());
 	                    		    clicked=true;
 	                    		  //  System.out.println("click:"+clicked);
 	                    	    }   
 	                        }
 	                        else{
 	                            //System.out.println("name move: "+b.getChessPiece(i,j).getName()+" color "+b.getChessPiece(i,j).getColor());
 	                            //System.out.println("xs:"+(int)save.getX()+" ys :"+(int)save.getY() );
 	                            //System.out.println("xe:"+i+" ye :"+j );
 	                            //System.out.println("valid:"+choosen.moveValid((int)save.getX(),(int)save.getY() , i, j));
 	                        	boolean q= false;
 	                        	//
 	                        	//
 	                            // Promote//
 	                        	//
 	                        	//
 	                        	if(choosen.getName().equals("P")) {
 	                        		if((int)save.getY()==1&&j==0&&!b.checkKing(b.posK1(), getColor())) {
 	                        			String s="";
 	                        			int c=0;
 	                        			while(s==null||s.length()==0)
 	                        			{
 	                        				Object[] possibilities = {"Queen", "Knight", "Bishop","Castle"};
 	 	                        			s = (String)JOptionPane.showInputDialog(null,"Promote Pawn to ","Promotion",JOptionPane.PLAIN_MESSAGE,icon,possibilities,"Queen");
 	                        			}
 	                        			if(s.contentEquals("Queen")) {
 	                        				b.addPiece(new Queen(getColor(),b), i, j);
 	                        				b.addPiece(new ChessPiece(b), (int)save.getX(),(int)save.getY());
 	                        				c=1;
 	                        			}
 	                        			else if(s.contentEquals("Knight")) {
 	                        				b.addPiece(new Knight(getColor(),b), i, j);
 	                        				b.addPiece(new ChessPiece(b), (int)save.getX(),(int)save.getY());
 	                        				c=2;
 	                        			}
 	                        			else if(s.contentEquals("Bishop")) {
 	                        				b.addPiece(new Bishop(getColor(),b), i, j);
 	                        				b.addPiece(new ChessPiece(b), (int)save.getX(),(int)save.getY());
 	                        				c=3;
 	                        			}
 	                        			else if(s.contentEquals("Castle")) {
 	                        				b.addPiece(new Castle(getColor(),b,"l"), i, j);
 	                        				b.addPiece(new ChessPiece(b), (int)save.getX(),(int)save.getY());
 	                        				c=4;
 	                        			}
 	                        			buttons[j][i].setIcon(b.getChessPiece(i, j).getIcon());
 	                        	    	buttons[(int)save.getY()][(int)save.getX()].setIcon(b.getChessPiece((int)save.getX(), (int)save.getY()).getIcon());
 	                        	    	moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);moves.add(c);
 	                        			q=true;
 	                        			moved=true;
 	                        		}
 	                        	}                 
 	                        	boolean c= false;
 	                        	//
 	                        	//
 	                        	//Castle//
 	                        	//
 	                        	//
 	                        	if(choosen.getName().equals("K")&&q==false) {
 	                        		if(b.posK1().equals(new Point(4,7))) {
 	                        			if(i==(int)save.getX()+2&&j==(int)save.getY()&&b.getCastle1()&&!b.checkKing(b.posK1(),getColor())&&b.getChessPiece(i-1, j).noPiece()&&b.getChessPiece(i, j).noPiece()&&!b.checkKing(new Point(i-1,j),getColor())&&!b.checkKing(new Point(i-2,j),getColor())) {
 	 	                        			movePiece((int)save.getX(),(int)save.getY(),i,j);
 	 	                        			movePiece(i+1,j,i-1,j);
 	 	                        			moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);
 	 	                        			moves.add(i+1);moves.add(j);moves.add(i-1);moves.add(j);
 	 	                        			b.updateK1(i, j);
 		                    		    	b.setCastle1(false);
 	 	                        			c=true;
 	 	                        			moved=true;
 	 	                        		}
 	 	                        		else if(i==(int)save.getX()-3&&j==(int)save.getY()&&b.getCastle2()&&!b.checkKing(b.posK1(),getColor())&&b.getChessPiece(i+1, j).noPiece()&&b.getChessPiece(i+2, j).noPiece()&&b.getChessPiece(i, j).noPiece()&&!b.checkKing(new Point(i+3,j),getColor())&&!b.checkKing(new Point(i+1,j),getColor())&&!b.checkKing(new Point(i+2,j),getColor())) {
 	 	                        			movePiece((int)save.getX(),(int)save.getY(),i,j);
 	 	                        			movePiece(i-1,j,i+1,j);
 	 	                        			moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);
 	 	                        			moves.add(i-1);moves.add(j);moves.add(i+1);moves.add(j);
 	 	                        			b.updateK1(i, j);
 		                    		    	b.setCastle2(false);
 	 	                        			c=true;
 	 	                        		    moved=true;
 	 	                        		}
 	                        		}
 	                        		else if(b.posK1().equals(new Point(3,7))) {
 	                        			if(i==(int)save.getX()+3&&j==(int)save.getY()&&b.getCastle1()&&!b.checkKing(b.posK1(),getColor())&&b.getChessPiece(i-1, j).noPiece()&&b.getChessPiece(i-2, j).noPiece()&&b.getChessPiece(i, j).noPiece()&&!b.checkKing(new Point(i-1,j),getColor())&&!b.checkKing(new Point(i-3,j),getColor())&&!b.checkKing(new Point(i-2,j),getColor())) {
 	 	                        			movePiece((int)save.getX(),(int)save.getY(),i,j);
 	 	                        			movePiece(i+1,j,i-1,j);
 	 	                        			moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);
 	 	                        			moves.add(i+1);moves.add(j);moves.add(i-1);moves.add(j);
 	 	                        			b.updateK1(i, j);
 		                    		    	b.setCastle1(false);
 	 	                        			c=true;
 	 	                        			moved=true;
 	 	                        		}
 	 	                        		else if(i==(int)save.getX()-2&&j==(int)save.getY()&&b.getCastle2()&&!b.checkKing(b.posK1(),getColor())&&b.getChessPiece(i+1, j).noPiece()&&b.getChessPiece(i, j).noPiece()&&!b.checkKing(new Point(i+1,j),getColor())&&!b.checkKing(new Point(i+2,j),getColor())) {
 	 	                        			movePiece((int)save.getX(),(int)save.getY(),i,j);
 	 	                        			movePiece(i-1,j,i+1,j);
 	 	                        			moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);
 	 	                        			moves.add(i-1);moves.add(j);moves.add(i+1);moves.add(j);
 	 	                        			b.updateK1(i, j);
 		                    		    	b.setCastle2(false);
 	 	                        			c=true;
 	 	                        		    moved=true;
 	 	                        		}
 	                        		}
 	                        	}
 	                        	//
 	                        	//
 	                        	//normal move//
 	                        	//
 	                        	//
 	                    	    if(choosen.moveValid((int)save.getX() ,(int)save.getY(), i, j)&&c==false&&q==false) {
 	                    	    	ChessPiece p= b.getChessPiece(i, j);
 	                                b.addPiece(choosen, i, j);
 	                    		    b.addPiece(new ChessPiece(b),(int)save.getX() , (int)save.getY());
 	                    		    if(choosen.getName().contentEquals("K")) {
 	                    		    	b.updateK1(i, j);
 	                    		    }
 	                    		    if(b.checkKing(b.posK1(), getColor())) {
 	                    		    	b.addPiece(p, i, j);
 	                    		    	b.addPiece(choosen, (int)save.getX(), (int)save.getY());
 	                    		       if(choosen.getName().contentEquals("K")) {
 	 	                    		    	b.updateK1( (int)save.getX(), (int)save.getY());
 	 	                    		    }
 	                    		    }
 	                    		    else {
 	                    		    	if(choosen.getName().contentEquals("K")) {
 	                    		    		b.updateK1(i, j);
 	                    		    		b.setCastle1(false);
 	                    		    		b.setCastle2(false);
 	                    		    	}
 	                    		    	if(choosen.getName().contentEquals("C")) {
 	                    		    		if(choosen.getSide().equals("l")) {b.setCastle2(false);}
 	                    		    		else {b.setCastle2(false);}
 	                    		    	}
 	                    		    	buttons[j][i].setIcon(icon);
 	 	                    		    buttons[(int)save.getY()][(int)save.getX()].setIcon(null);   
 	 	                    		    moves.add((int)save.getX());moves.add((int)save.getY());moves.add(i);moves.add(j);
 	 	                    		    moved=true;
 	                    		    }
 	                    	    }
 	                            clicked=false;
 	                        }
 	        		    }
 	                }
 	            });
    	        buttonpanel.add(buttons[i][j]);
    		    }
    	    }
        }
    public void loadIcon() {
    	for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
    			buttons[j][i].setIcon(b.getChessPiece(i, j).getIcon());
    		}
    	}
    }
    public void movePiece(int xs, int ys, int xe, int ye) {
    	b.movePiece(xs, ys, xe, ye);
    	buttons[ye][xe].setIcon(b.getChessPiece(xe, ye).getIcon());
    	buttons[ys][xs].setIcon(b.getChessPiece(xs, ys).getIcon());
    }
    public void movePiece(int xs, int ys, int xe, int ye,int c) {
    	if(c==1) {b.addPiece(new Queen(ecolor,b), xs, ys);}
    	else if(c==2) {b.addPiece(new Knight(ecolor,b), xs, ys);}
    	else if(c==3) {b.addPiece(new Bishop(ecolor,b), xs, ys);}
    	else {b.addPiece(new Castle(ecolor,b,"f"), xs, ys);}
    	b.movePiece(xs, ys, xe, ye);
    	buttons[ye][xe].setIcon(b.getChessPiece(xe, ye).getIcon());
    	buttons[ys][xs].setIcon(b.getChessPiece(xs, ys).getIcon());
    }
    public void loadboard(Board b) {
    	this.b=b;
    }
    private class PlayPanel extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(playboard.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void enable() {}
    public void disable() {}
    public void move() {
    	this.turn =true;
    	this.moved=false;
        moves.clear();
    }
    public ArrayList<Integer> getMoves() {return moves;}
    public boolean lose() {
    	return b.checkKing(b.posK1(), getColor())&&stalemate();
    }
    public boolean stalemate() {
    	int count =0;
      //  for(int i=0;i<8;i++) {
      //  	for(int j=0;j<8;j++) {
      //  		buttons[i][j].setContentAreaFilled( false );
      //  	}
      //  }
    	for(int i=0; i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if(b.getChessPiece(i, j).getColor().contentEquals(getColor())) {
    			    for(int x=0;x<8;x++) {
    			    	for(int y=0;y<8;y++) {
    			    		if(b.getChessPiece(i, j).moveValid(i, j, x, y)) {
    			    			//buttons[y][x].setContentAreaFilled( true );
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
    public void pause() {
    	turn =false;
    }
    public boolean notMove() {return !moved;}
    public String getColor() {return color;}
    public void announce(String s) {System.out.println("Player "+s+" win");}
    public void draw() {System.out.println("stalemate");}
    public void nextScence() {}
}
