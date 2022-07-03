
//  שים לב:להוריד ספריית Jsoup גרסה 1.14.3 (1.5 עושה בעיות..)

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomePanel extends JPanel {

    final int WIDTH_HEIGHT = 1000;
    final int LABEL_WIDTH_HEIGHT = 350;
    final int DISTANCE = 250;
    final int X_Y_START = 0;
    final int BUTTON_FIELD_WIDTH = 100;
    final int BUTTON_FIELD_HEIGHT = 50;
    final int X_Y_USER_NAME_FIELD = 350;
    final int Y_FACEBOOK_BOXES_ILUSTTRATE = 300;
    final int Y_TOP = 100;


    private Font font = new Font("", Font.BOLD, 17);
    private JTextField nameSerch;
    private JTextField userName;
    private JTextField userPassword;
    private JButton downlandPicture;
    private ChromeDriver driver;
    private JLabel ilustrete;
    private JLabel top;
    private BufferedImage theBufferedImage = null; //  הbuffered image הסופי!



    public HomePanel() {
        //  הגדרת סלניום
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Desktop\\intelij\\chromedriver.exe");
        if (new File("C:\\Users\\lenovo\\Desktop\\intelij").exists()) { // בדיקה האם קובץ קיים
            System.out.println("OK");
        }

        driver = new ChromeDriver();
        //חריגה במידה ועמוד זה שינה כתובת
        String homeWeb = new String("https://he-il.facebook.com/"); // קישור לאתר פייסבוק
        driver.get(homeWeb);

        //  הגדרות הפאנל
        this.setSize(WIDTH_HEIGHT, WIDTH_HEIGHT);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

        top = new JLabel("after pushing on login button white for a massage!! ");
        top.setBounds(WIDTH_HEIGHT / 3, Y_TOP, WIDTH_HEIGHT, BUTTON_FIELD_HEIGHT);
        top.setForeground(Color.WHITE);
        top.setFont(font);
        top.setVisible(true);
        this.add(top);

        // טקסט נילווה + שם משתמש
        userName = new JTextField();
        userName.setBounds(X_Y_USER_NAME_FIELD, X_Y_USER_NAME_FIELD, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        userName.setFont(font);
        userName.setBackground(Color.WHITE);
        userName.setVisible(true);
        this.add(userName);
        System.out.println(userName.getX() + "," + userName.getY());
        JLabel nameIlustrete = new JLabel("enter your mail:");
        nameIlustrete.setBounds(userName.getX(), Y_FACEBOOK_BOXES_ILUSTTRATE, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        nameIlustrete.setForeground(Color.WHITE);
        nameIlustrete.setFont(font);
        nameIlustrete.setVisible(true);
        this.add(nameIlustrete);

        // טקסט נילווה + סיסמא
        userPassword = new JTextField();
        userPassword.setBounds(X_Y_USER_NAME_FIELD + BUTTON_FIELD_WIDTH * 3, X_Y_USER_NAME_FIELD, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        userPassword.setFont(font);
        userPassword.setBackground(Color.WHITE);
        userPassword.setVisible(true);
        this.add(userPassword);

        JLabel passwordIlustrete = new JLabel("enter your password:");
        passwordIlustrete.setBounds(userPassword.getX(), Y_FACEBOOK_BOXES_ILUSTTRATE, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        passwordIlustrete.setForeground(Color.WHITE);
        passwordIlustrete.setFont(font);
        passwordIlustrete.setVisible(true);
        this.add(passwordIlustrete);

        // טקסט נילווה + תיבת חיפוש שם מהפייסבוק
        nameSerch = new JTextField();
        nameSerch.setBounds(LABEL_WIDTH_HEIGHT + (DISTANCE / 2), (WIDTH_HEIGHT / 2) + DISTANCE / 2, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        nameSerch.setFont(font);
        nameSerch.setBackground(Color.WHITE);
        nameSerch.setVisible(true);

        ilustrete = new JLabel("enter a name person:");
        ilustrete.setBounds(LABEL_WIDTH_HEIGHT + (DISTANCE / 2), (nameSerch.getY()) - 40, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        ilustrete.setForeground(Color.WHITE);
        ilustrete.setFont(font);
        ilustrete.setVisible(true);

        //  כפתור הורדת התמונה מהאינטרנט
        downlandPicture = new JButton("Downland photo");
        downlandPicture.setBounds(nameSerch.getX(), nameSerch.getY() + BUTTON_FIELD_HEIGHT * 2, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        downlandPicture.setBackground(Color.GREEN);
        downlandPicture.setFont(font);
        downlandPicture.setVisible(true);
        downlandPicture.addActionListener(event2 -> {
            String name = validationToUrl(nameSerch.getText());
            try {

                String urlName = new String("https://www.facebook.com/" + name); // קישור לתמונה
                driver.get(urlName);

                // WebElement x = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div/div[1]/div[2]/div/div/div/div[1]/div/a/div/svg/g/image"));
                //  String link = x.getAttribute("href");

                List<WebElement> allLinks = driver.findElements(By.cssSelector("link"));  //   חשוב:  import java.util.List; ||  מציאת כל האלמנטים עם קישור
                String[] linksByHref = new String[allLinks.size()]; // מערך שמירת הקישורים הבלבד
                List<BufferedImage> allImages = new ArrayList<BufferedImage>();
                int matchSizeCounter = 0;
                for (int i = 0; i < linksByHref.length; i++) {
                    linksByHref[i] = allLinks.get(i).getAttribute("href"); // שמירת הלינקים בלבד
                    if (linksByHref[i].substring(linksByHref[i].length() - 11, linksByHref[i].length() - 6).equals("oe=62")) { // מבדיקה ידנית יודע שבמיקום מסויים בלינק של תמונת הפרופיל יופיע הצירוף הנ"ל
                        BufferedImage bufferedImage = ImageIO.read(new URL(linksByHref[i])); // שמירה כbuuferedImage והוספה לרשימה המיועדת
                        allImages.add(bufferedImage);
                        System.out.println(linksByHref[i] + "!!!!");
                    }
                }
                for (int i = 0; i < allImages.size(); i++) { // מבדיקה ידנית נובע שתמונת הפרופיל תמיד תופיע ב60X60 ובלינק השני בגודל הזה
                    System.out.println(allImages.get(i).getWidth() + " , " + allImages.get(i).getHeight());
                    matchSizeCounter++;
                    if (allImages.get(i).getWidth() == 60 && allImages.get(i).getHeight() == 60 && matchSizeCounter > 1) {
                        theBufferedImage = allImages.get(i);
                        break;
                    }
                }
                try {
                    theBufferedImage.getScaledInstance(350,350, Image.SCALE_SMOOTH); //  מתודה שמותחת לי את הציור על כל גודל הlabel
                    System.out.println(theBufferedImage.getWidth()+","+theBufferedImage.getHeight());
                } catch (Exception e){
                    System.out.println("samthing in size wrong!");
                }

                // ImageIO.write(bufferedImage,"jpg" , outputFile); //שמירת הקובץ במחשב
                MainPanel mainPanel = new MainPanel(); //  פתיחת החלון שבו יופיעו התמונה והעריכה
                mainPanel.setVisible(true);
                mainPanel.setBufferedImage(theBufferedImage);
                mainPanel.setTemp(theBufferedImage);
                mainPanel.setPictureToSourceLabel();
                mainPanel.setPictureToProcessedLabel();


// arielevy17@gmail.com       317706463P95
            } catch (Exception e) { //  הודעה על אי מציאת תמונת האיש שהוזן
                JFrame wrongMessageFrame = new JFrame();
                wrongMessageFrame.setSize(WIDTH_HEIGHT / 3, WIDTH_HEIGHT / 4);
                JLabel wrongFrameText = new JLabel("this person photo not found!" + "\n (first note that you are logged in!)");
                wrongFrameText.setSize(WIDTH_HEIGHT / 4, WIDTH_HEIGHT / 4);
                wrongFrameText.setForeground(Color.RED);
                wrongFrameText.setFont(font);
                wrongFrameText.setVisible(true);
                wrongMessageFrame.add(wrongFrameText);
                wrongMessageFrame.setVisible(true);
                System.out.println("this person photo not found!");
            }

        });

        // כפתור התחברות לחשבון פייסבןק
        JButton loginFacebook = new JButton("Login");
        loginFacebook.setBounds(nameSerch.getX(), userName.getY() + BUTTON_FIELD_WIDTH, BUTTON_FIELD_WIDTH * 2, BUTTON_FIELD_HEIGHT);
        loginFacebook.setBackground(Color.blue);
        loginFacebook.setFont(font);
        loginFacebook.setVisible(true);
        loginFacebook.addActionListener((event1 -> {
            driver.get(homeWeb); // החילוץ נעשה מדף מסויים ולכן נחזור אליו כדי שהאלמנטים יתאימו
            //  הדבקת המייל והסיסמא מהמשתמש לדפדפן
            WebElement userName = driver.findElement(By.id("email")); // הזנת מייל במקום המיועד לכך
            userName.sendKeys(this.userName.getText());  // הדבקת טסקט בתיבת טקסט
            WebElement password = driver.findElement(By.id("pass")); // הזנת סיסמא במקום המיועד לכך
            password.sendKeys(this.userPassword.getText());  // הדבקת טסקט בתיבת טקסט
            WebElement logIn = driver.findElement(By.name("login"));
            logIn.click();
            Thread t1 = new Thread(() -> {
                checkConnecting(); //  מתודה לבדיקת הצלחת התחברות
            });
            t1.start();
        }));
        this.add(loginFacebook);
        downlandPicture.requestFocus();

    }

    public BufferedImage getTheBufferedImage() {
        return theBufferedImage;
    }

    //  מתודה להמרת הטקסט למחובר ע"מ שיתחבר לURL ויצור URL בתבנית הנדרשת
    public String validationToUrl(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                name = name.substring(0, i) + name.substring(i + 1);
            }
        }
        System.out.println(name);
        return name;
    }

    public void checkConnecting() {  //  מתודה לבדיקת הצלחת התחברות
        try {
            WebElement connectElement = driver.findElement(By.cssSelector("a[href*='/notifications/']"));
            System.out.println("connect sucessfuly");
            top.setText("connect sucessfuly!");
            top.setForeground(Color.GREEN);
            // רק לאחר התחברות לפייסבוק יופיע הממשקים הקשורים לעריכת התמונה
            this.add(ilustrete);
            this.add(nameSerch);
            this.add(downlandPicture);
        } catch (Exception e) {
         checkConnecting();
        }
    }

}




