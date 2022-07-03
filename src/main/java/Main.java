import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {

        new Main();
    }
    public Main(){
        final int WIDTH_HEIGHT=1000;
        //   הגדרות הפריים
        this.setSize(WIDTH_HEIGHT,WIDTH_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        HomePanel mainPanel = new HomePanel();
        mainPanel.setVisible(true);
        this.add(mainPanel);
    }
}

