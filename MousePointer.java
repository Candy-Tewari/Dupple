import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Robot;
class MousePointer{
   JFrame f;
   JTextField t1, t2;
   JButton b1, b2;
   JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
   private MousePointer(){
        f=new JFrame("Dupple");
        t1=new JTextField("");
        t2=new JTextField("");
        b1=new JButton("Find");
        b2=new JButton("Exit");
        l1=new JLabel("x      0000");
        l2=new JLabel("y      0000");
        l3=new JLabel("red                   255");
        l4=new JLabel("green               255");
        l5=new JLabel("blue                  255");
        l6=new JLabel("hexadecimal    255");
        l7=new JLabel("x ");
        l8=new JLabel("y ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        l9=new JLabel("Your width  "+width);
        l10=new JLabel("Your height  "+height);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setSize(400, 250);
        f.setResizable(false);
   }
   private void setComponents(){
        l1.setBounds(50, 20, 200, 50);
        l2.setBounds(50, 50, 200, 50);
        l3.setBounds(230, 20, 200, 50);
        l4.setBounds(230, 46, 200, 50);
        l5.setBounds(230, 72, 200, 50);
        l6.setBounds(230, 96, 200, 50);
        l7.setBounds(50, 105, 200, 50);
        t1.setBounds(75, 120, 70, 20);
        l8.setBounds(50, 136, 200, 50);
        t2.setBounds(75, 150, 70, 20);
        b1.setBounds(80, 178, 60, 30);
        b2.setBounds(150, 178, 60, 30);
        b2.addActionListener(new ExitListener());
        l9.setBounds(230, 150, 200, 50);
        l10.setBounds(230, 170, 200, 50);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(l7);
        f.add(t1);
        f.add(l8);
        f.add(t2);
        f.add(b1);
        f.add(l9);
        f.add(l10);
        f.add(b2);
        f.setVisible(true);
   }
   private class EventHandler implements ActionListener{
        private Robot robot;
        EventHandler(Robot robot){
            this.robot=robot;
        }
        public void actionPerformed(ActionEvent e){
            try{
                Thread.sleep(500);
                robot.mouseMove(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
            }
            catch(Exception error){}
        }
   }
   private class ExitListener implements ActionListener{
       public void actionPerformed(ActionEvent e){
           System.exit(0);
       }
   }
   private void startPointer()throws Exception{
        Robot robot=new Robot();
        b1.addActionListener(new EventHandler(robot));
        while(true){
            Point p = MouseInfo.getPointerInfo().getLocation(); 
            int x = p.x; 
            int y = p.y;
            Color color=robot.getPixelColor(x, y);
            l1.setText("x      "+x);
            l2.setText("y      "+y);
            l3.setText("red                   "+color.getRed());
            l4.setText("green               "+color.getGreen());
            l5.setText("blue                  "+color.getBlue());
            l6.setText("hexadecimal    "+Integer.toHexString(color.getRGB() & 0xffffff));
        }
   }
   public static void main(String[]args)throws Exception{
        MousePointer mp=new MousePointer(); 
        mp.setComponents();
        mp.startPointer();
   }
}