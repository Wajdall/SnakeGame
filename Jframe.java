import javax.swing.JFrame;

public class Jframe extends JFrame{
    Jframe(){
        SnakePanel p1 =new SnakePanel();
        this.add(p1);  
        this.setTitle(" SNAKE ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        p1.startGame();
        
    }
}
