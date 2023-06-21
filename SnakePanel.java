import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener {

    static final int ScreenW = 600;
    static final int Screenh = 600;
    static final int Unit = 25;
    static final int gameu = (ScreenW * Screenh) / Unit;
    static final int Deley = 75;
    int x[] = new int[gameu];
    int y[] = new int[gameu];
    int bodyParts = 6;
    int appleseaten;
    int appleX;
    int appleY;
    char dirction = 'R';
    boolean running = true;
    Random RR;
    Timer timer;

    SnakePanel() {
        RR = new Random();
        this.setPreferredSize(new Dimension(ScreenW, Screenh));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(Deley, this);
        timer.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(running)
        draw(g);
        else 
        GameOver(g);

    }

    public void draw(Graphics g) {
        
if(running){
        for (int i = 0; i < (Screenh / Unit); i++) {
            g.drawLine(i * Unit, 0, i * Unit, Screenh);
            g.drawLine(0, (i * Unit), ScreenW, i * Unit);

        }  
         g.setColor(Color.RED);
              g.fillOval(appleX, appleY, Unit, Unit);
       

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], Unit, Unit);
            } else {
                g.setColor(new Color(RR.nextInt(225),RR.nextInt(225),RR.nextInt(225)));
                g.fillRect(x[i], y[i], Unit, Unit);
            }

            
       

        }
Font font = new Font("Ink free", Font.BOLD, 45);
    g.setColor(Color.WHITE);
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics(font); // use the same font for calculating the width
    g.drawString("SCORE :" +appleseaten, (ScreenW - metrics.stringWidth("SCORE :" +appleseaten)) / 2, g.getFont().getSize());



    }
        else 
        GameOver(g);
        

    }

    public void newApple() {
        appleX = RR.nextInt((int) (ScreenW / Unit)) * Unit;
        appleY= RR.nextInt((int) (Screenh / Unit)) * Unit;

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (dirction) {
            case 'U':
                y[0] = y[0] - Unit;
                break;
            case 'D':
                y[0] = y[0] + Unit;
                break;
            case 'L':
                x[0] = x[0] - Unit;
                break;
            case 'R':
                x[0] = x[0] + Unit;
                break;
        }
        

    }

    public void checkApple() {
        if((x[0]==appleX)&&(y[0]==appleY)){
            bodyParts++;
            appleseaten++;
            newApple();
        }

    }

    public void checkCollisions() {
        for(int i =bodyParts;i>0;i--){
            if((x[0]==x[i])&&(y[0]==y[i])){
                running=false;

            }
        }
        if(x[0]<0){
            running=false;
        }
         if(x[0]>ScreenW){
            running=false;
        }
         if(y[0]<0){
            running=false;
        }
         if(y[0]>Screenh){
            running=false;
        }
        if(!running){
            timer.stop();
        }
        

    }

    public void GameOver(Graphics g) {
       
    
    Font font = new Font("Ink free", Font.BOLD, 75);
    g.setColor(Color.red);
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics(font); // use the same font for calculating the width
    g.drawString("game over", (ScreenW - metrics.stringWidth("game over")) / 2, Screenh/2);

Font font2 = new Font("Ink free", Font.BOLD, 45);
    g.setColor(Color.red);
    g.setFont(font);
    FontMetrics metrics2 = g.getFontMetrics(font); // use the same font for calculating the width
    g.drawString("SCORE :" +appleseaten, (ScreenW - metrics.stringWidth("SCORE :" +appleseaten)) / 2, g.getFont().getSize());



    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

/*     public class MyKeyAdapter extends KeyAdapter {
        public void keypressed(KeyEvent e) {

            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                if(dirction!='R'){
                    dirction='L';
                }
                break;
                 case KeyEvent.VK_RIGHT:
                if(dirction!='L'){
                    dirction='R';
                }
                break; case KeyEvent.VK_UP:
                if(dirction!='D'){
                    dirction='U';
                }
                break; case KeyEvent.VK_DOWN:
                if(dirction!='U'){
                    dirction='D';
                }
                break;

            }
        }
    } */
    public class MyKeyAdapter extends KeyAdapter {
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            if(dirction!='R'){
                dirction='L';
            }
            break;
             case KeyEvent.VK_RIGHT:
            if(dirction!='L'){
                dirction='R';
            }
            break; case KeyEvent.VK_UP:
            if(dirction!='D'){
                dirction='U';
            }
            break; case KeyEvent.VK_DOWN:
            if(dirction!='U'){
                dirction='D';
            }
            break;

        }
    }
}

}
