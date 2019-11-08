import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    private int delay = 100;
    Random random = new Random();
    Timer timer = new Timer(delay, this);
    private int level = 1;
    private int noktalar = 3;
    private int x[] = new int[900];
    private int y[] = new int[900];
    private boolean sag = true;
    private boolean sol = false;
    private boolean yukari = false;
    private boolean asagi = false;
    private int elmaX;
    private int elmaY;
    
    

    public Oyun() {
        setBackground(Color.BLACK);
        elmaX=10*random.nextInt(28);
        elmaY=10*random.nextInt(26);
        
        for (int i=0; i<noktalar; i++) {
            x[i] = 50 - i*10;
            y[i] = 50;
        }
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        
        g.setColor(Color.red);
        g.fillOval(elmaX, elmaY, 10, 10);
        
        for(int i=0;i<noktalar;i++){
            if(i==0){
                g.setColor(Color.blue);
                g.fillOval(x[i], y[i], 10, 10);
            }
            else{
                g.setColor(Color.green);
                g.fillOval(x[i], y[i], 10, 10);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!sag)) {
                sol = true;
                yukari = false;
                asagi = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!sol)) {
                sag = true;
                yukari = false;
                asagi = false;
            }

            if ((key == KeyEvent.VK_UP) && (!asagi)) {
                yukari = true;
                sag = false;
                sol = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!yukari)) {
                asagi = true;
                sag = false;
                sol = false;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hareketEt();
        yediMi();
        carpismaKontrol();
        repaint();
    }
    
    private void hareketEt(){
        for (int z = noktalar; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (sol) {
            x[0] -= 10;
        }

        if (sag) {
            x[0] += 10;
        }

        if (yukari) {
            y[0] -= 10;
        }

        if (asagi) {
            y[0] += 10;
        }
    }
    
    private void yediMi(){
        if(x[0]==elmaX && y[0]==elmaY){
            noktalar++;
            level++;
            elmaX=10*random.nextInt(28);
            elmaY=10*random.nextInt(26);
        }
    }
    
    private void carpismaKontrol(){
        for(int i=1; i<noktalar; i++){
            if(x[0]==x[i] && y[0]==y[i]){
                gameOver();
            }
        }
        if(x[0] < 0 || x[0] > 280){
            gameOver();
        }
        if(y[0] < 0 || y[0] > 260){
            gameOver();
        }
    }
    
    private void gameOver(){
        timer.stop();
        String message = "Oyun Bitti! Level: " + level;
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }
}
