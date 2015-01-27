package pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class PongGame extends JComponent implements ActionListener, MouseMotionListener,  KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ballX = 300, ballY = 50;
	private int paddleX = 100, paddleY = 560;
	private int velX = 6, velY = 6;
	private int ballXSpeed = velX, ballYSpeed = velX;
	private int pontos = 0;
	
	//player2
	public int playerX = 20, playerY = 20;
	//public int velPlayerX = 10, velPlayerY = 10;
	private int iniciar;
	public static void main (String [] args){
		JFrame window = new JFrame("Pong Game by Victor");
		PongGame game = new PongGame();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		Timer t = new Timer (20, game);
		t.start();
		
		window.addKeyListener(game);
		window.addMouseMotionListener(game);
		
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(32,123,232));
		g.fillRect(0,0,800,600);
		
		g.setColor(new Color(199,30,81));
		g.fillRect(paddleX,paddleY,150,15);
	
			g.setColor(Color.black);
			g.fillOval(ballX,ballY,20,20);
	
		g.drawString("pontos: "+pontos, 0, 10);
		g.setFont(new Font("Kristen ITC",2, 20));  
		g.drawString("Consiga 8 PONTOS", 300 , 220);
		// jogador 2
		
		g.setColor(new Color(141, 215, 190));
		g.fillRect(playerX, playerY, 150, 15);
		
		
		//DoubleBuffering
		g.dispose();
		setFocusable(true);
		setDoubleBuffered(true);
	}
	
	public void actionPerformed(ActionEvent e){
		ballX += ballXSpeed;
		ballY += ballYSpeed;
		if (ballX >= paddleX && ballX <= paddleX + 150 && ballY >= 540){
			ballYSpeed= -velY;
			nextPhase();
			pontos++;
		}
		if (ballX >= 800 - 20){
			ballXSpeed= -velX;
		}
		if (ballX <= 0){
			ballXSpeed = velX;
		}
		if (ballX >= playerX && ballX <= playerX + 150 && ballY <= 20){
			ballYSpeed = velY;
			nextPhase();
			pontos++;
		}
		
		if ( ballY >= 590 && pontos != 8 ){
			iniciar = JOptionPane.showConfirmDialog(null, "Deseja reiniciar ?"," Voce perdeu!", JOptionPane.YES_NO_OPTION);
				if (iniciar == JOptionPane.NO_OPTION){
					System.exit(0);
				}else{
					pontos = 0;
					ballX = 300;   
					ballY = 50;   velX = 6; velY = 6;
					ballYSpeed= velY;
				}
		}
		
		if ( ballY <= 0 && pontos != 8 ){
			iniciar = JOptionPane.showConfirmDialog(null, "Deseja reiniciar ?"," Voce perdeu!", JOptionPane.YES_NO_OPTION);
			if (iniciar == JOptionPane.NO_OPTION){
				System.exit(0);
			}else{
				pontos = 0;
				ballX = 300;
				ballY = 50;  	 velX = 6; velY = 6;
				ballYSpeed= velY;
			}
		}
		//CAMPEAO
		if(pontos == 8){
			JOptionPane.showMessageDialog(null, "Você Conseguiu "+pontos+"! :)","Parabéns!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		//Nao deixar ultraapassar a tela os PADDLE
		if (paddleX >= 800-150){
			paddleX = 800-150;
		}
		if (paddleX <= 0){
			paddleX = 0;
		}
		if (playerX >= 800-150){
			playerX = 800-150;
		}
		if (playerX <= 0){
			playerX = 0;
		}
		
		
		
		repaint();
	}
	
	public void nextPhase(){
		velX += 2;
		velY += 2;
	}

	
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		paddleX = e.getX() - 80;
		repaint();
	}
	
	//PLAYER 2 ACTION
			
		
	@Override
	public void keyReleased(KeyEvent e) {
	int codigo = e.getKeyCode();
	
		
		if(codigo == KeyEvent.VK_LEFT){
			playerX += -90;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			playerX += 90;
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent tecla) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int codigo = e.getKeyCode();
		
		if(codigo == KeyEvent.VK_LEFT){
			playerX += -90;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			playerX += 90;
		}
		
		
		
	}
	

	
	
}
