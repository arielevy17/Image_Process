import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends  JFrame {

        final int WIDTH_HEIGHT=1000;
        final int LABEL_WIDTH_HEIGHT=350;
        final int DISTANCE=50;
        final int X_Y_START=0;
        final int BUTTON_FIELD_WIDTH=170;
        final int BUTTON_FIELD_HEIGHT=50;
        final int Y_START_FILTERS=100;
        final int X_START_FILTERS =LABEL_WIDTH_HEIGHT+DISTANCE*2;


        private Font font = new Font("", Font.BOLD, 17);
        private JLabel source;
        private JLabel processed;
        private BufferedImage bufferedImage=null;
        private BufferedImage temp=null;

        public MainPanel(){
            //  הגדרות הפאנל
            this.setSize(WIDTH_HEIGHT,WIDTH_HEIGHT);
            this.setLayout(null);
            this.setVisible(true);

        //  הגדרות הLABEL הראשון- התמונה המקורית
            source = new JLabel();
            source.setBounds(DISTANCE,WIDTH_HEIGHT-LABEL_WIDTH_HEIGHT,LABEL_WIDTH_HEIGHT,LABEL_WIDTH_HEIGHT);
            source.setBackground(Color.black);
            source.setVisible(true);
        this.add(source);
            System.out.println(source.getWidth()+","+source.getHeight());

        //  הגדרות הLABEL השני- התמונה הערוכה
            processed = new JLabel();
            processed.setForeground(Color.WHITE);
            processed.setBounds((WIDTH_HEIGHT/2)+DISTANCE,WIDTH_HEIGHT-LABEL_WIDTH_HEIGHT,LABEL_WIDTH_HEIGHT,LABEL_WIDTH_HEIGHT);
            processed.setBackground(Color.black);
            processed.setVisible(true);
        this.add(processed);
            System.out.println(processed.getWidth()+","+processed.getHeight());

         //  פילטר ניגודיות
            JButton constantB = new JButton("Constant");
            constantB.setBounds(X_START_FILTERS,X_Y_START,BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
            constantB.setFont(font);
            constantB.setVisible(true);
            this.add(constantB);
            constantB.addActionListener(event -> {  // לבדוק האם במתודה הבאפרד משתנה
                returnToSource();
                constant(temp);
            });

         //  פילטר שחור לבן
        JButton grayScale = new JButton("Black/With");
        grayScale.setBounds(X_START_FILTERS,Y_START_FILTERS,BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
        grayScale.setFont(font);
        grayScale.setVisible(true);
        this.add(grayScale);
        grayScale.addActionListener(event -> {  // לבדוק האם במתודה הבאפרד משתנה
           returnToSource();
            graceScale(temp);
                });

            //  פילטר החלפת צבעים לימין
            JButton colorShiftRight = new JButton("Color shift R");
            colorShiftRight.setBounds(grayScale.getX(),grayScale.getY()+BUTTON_FIELD_HEIGHT+DISTANCE,BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
            colorShiftRight.setFont(font);
            colorShiftRight.setVisible(true);
            this.add(colorShiftRight);
            colorShiftRight.addActionListener(event -> {
                returnToSource();
                colorShiftR(temp);

            });

            //  פילטר החלפת צבעים לשמאל
            JButton colorShiftLeft = new JButton("Color shift L");
            colorShiftLeft.setBounds(grayScale.getX(),grayScale.getY()+((BUTTON_FIELD_HEIGHT+DISTANCE)*2),BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
            colorShiftLeft.setFont(font);
            colorShiftLeft.setVisible(true);
            this.add(colorShiftLeft);
            colorShiftLeft.addActionListener(event -> {
                returnToSource();
                colorShiftL(temp);

            });

            //  פילטר הבהרת תמונה
            JButton ligher = new JButton("Ligher");
            ligher.setBounds(grayScale.getX(),grayScale.getY()+((BUTTON_FIELD_HEIGHT+DISTANCE)*3),BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
            ligher.setFont(font);
            ligher.setVisible(true);
            this.add(ligher);
            ligher.addActionListener(event -> {
                returnToSource();
                ligther(temp);

            });

            //  פילטר הכההת תמונה
            JButton darkerB = new JButton("Darker");
            darkerB.setBounds(grayScale.getX(),grayScale.getY()+((BUTTON_FIELD_HEIGHT+DISTANCE)*4),BUTTON_FIELD_WIDTH,BUTTON_FIELD_HEIGHT);
            darkerB.setFont(font);
            darkerB.setVisible(true);
            this.add(darkerB);
            darkerB.addActionListener(event -> {
                returnToSource();
                darker(temp);

            });


        }

        public void graceScale(BufferedImage bufferedImage){
            for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                    Color color = new Color(pixel); // שמירה של הint כצבע
                    int red = color.getRed();
                    int blue = color.getBlue();
                    int green = color.getGreen();
                    int average = (red + green + blue)/3;
                    Color colorToSet = new Color(average, average, average);
                    bufferedImage.setRGB(x, y, colorToSet.getRGB());
                }
            }
            Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
            processed.setIcon(new ImageIcon(image));
        }

    public void constant(BufferedImage bufferedImage){
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel); // שמירה של הint כצבע
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color colorToSet = new Color(255-red,255-green,255-blue);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }
        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void colorShiftL(BufferedImage bufferedImage){
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel); // שמירה של הint כצבע
                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();
                Color colorToSet = new Color(blue, red, green);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }
        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void colorShiftR(BufferedImage bufferedImage){
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel); // שמירה של הint כצבע
                int green = color.getGreen();
                int blue = color.getBlue();
                int red = color.getRed();
                Color colorToSet = new Color(green, blue, red);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }
        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void ligther(BufferedImage bufferedImage){
            int ligtherFactor=100;
        int max=255;
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel); // שמירה של הint כצבע
                int red = color.getRed();
                if (red<=max-ligtherFactor) {
                    red += ligtherFactor;
                } else {
                    red=max;
                }
                int green = color.getGreen();
                if (green<=max-ligtherFactor) {
                    green += ligtherFactor;
                } else {
                    green=max;
                }
                int blue = color.getBlue();
                if (blue<=max-ligtherFactor) {
                    blue += ligtherFactor;
                } else {
                    blue=max;
                }
                Color colorToSet = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }
        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void darker(BufferedImage bufferedImage){
        int darkFactor=100;
        int max=10;
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel); // שמירה של הint כצבע
                int red = color.getRed();
                if (red>=max+darkFactor) {
                    red -= darkFactor;
                } else {
                    red=max;
                }
                int green = color.getGreen();
                if (green>=max+darkFactor) {
                    green -= darkFactor;
                } else {
                    green=max;
                }
                int blue = color.getBlue();
                if (blue>=max+darkFactor) {
                    blue -= darkFactor;
                } else {
                    blue=max;
                }
                Color colorToSet = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }
        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void returnToSource(){
        for (int x = 0; x < bufferedImage.getWidth(); x++) {  //  שתי for שירוצו על כל הפיקסלים שבתמונה
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int pixel = this.bufferedImage.getRGB(x, y); // קבלת הצבע מהפיקסל במיקום של הסוגריים(ע"י חזקת הRGB של הצבע , מקסימום 3^255 )
                Color color = new Color(pixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color colorToSet = new Color(red, green, blue);
                bufferedImage.setRGB(x, y, colorToSet.getRGB());
            }






        }
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }


        //  מתודה להזנת התמונה ב labels
    public void setPictureToSourceLabel() {
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
       source.setIcon(new ImageIcon(image));
    }

    public void setPictureToProcessedLabel() {
        Image image = bufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH);
        processed.setIcon(new ImageIcon(image));
    }

    public void setProcessed(JLabel processed) {
        this.processed = processed;
    }

    public void getPictureSize() {
        System.out.println(source.getIcon().getIconWidth()+","+source.getIcon().getIconHeight());
    }

    public JLabel getSource() {
        return source;
    }

    public void setSource(JLabel source) {
        this.source = source;
    }

    public JLabel getProcessed() {
        return processed;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        }

    public BufferedImage getTemp() {
        return temp;
    }

    public void setTemp(BufferedImage temp) {
        this.temp = temp;
    }
}
