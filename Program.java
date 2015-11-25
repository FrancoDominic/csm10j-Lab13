
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
I couldn't figure out how to get the program to repaint if the user clicks 
on the program after it has already painted once. Must be because I used 
JButton instead of just printing "Click me!" directly onto the panel (omitting 
JButton) and just having mouse listener as the trigger to create the ovals.
*/

public class Program extends JFrame
{
    public JButton button;
    private JPanel panel;
    
    public Program()
    {
        super("Draw Ovals");
        setSize(400, 400);
        setVisible(true);
        panel = new JPanel();
        button = new JButton("Click me! ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void button()//displays "Click me!" button
    {
        Container c = this.getContentPane();
        c.add(button);
        button.addActionListener(new MouseListener());
    }
    
    private class MouseListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource() == button)
            {
                button.setVisible(false);//removes button after being clicked
                
                Graphics oval = getGraphics();
                InputWindow iw = new InputWindow();
                iw.paint(oval);
            }
        }
    }
    
    private class InputWindow extends JFrame
    {
        String inputString = JOptionPane.showInputDialog("Enter number of ovals");
        int input = Integer.parseInt(inputString);
        
        public void draw(Graphics g)
        {
            int width = 15;//wifth of the first oval
            int height = 5;//height of the first oval
            int coord = 30;//coordinates of the first oval
            
            if (input < 1)
            {
                button.setVisible(true);//will keep displaying button
            }
        
            if (input > 20)//input defaults to 20 if user inputs anything over 20
            {
                input = 20;
                
                for(int i = 0; i < input; i++)
                {
                  g.drawOval(coord, coord, width, height);
                  width += 15;
                  height += 5;
                  coord += 5;
                }
            }
        
            else//for any input between 1 and 20
            {
                for(int i = 0; i < input; i++)
                {
                  g.drawOval(coord, coord, width, height);
                  width += 15;
                  height += 5;
                  coord += 5;
                }
            }
        }
        
        public void paint(Graphics g)
        {
            button.setVisible(false);
            g.setColor(Color.RED);
            super.paint(g);
            draw(g);
        }
  }
      
      public static void main(String [] args)
      {
          Program p = new Program();
          p.button();
          p.setVisible(true);
      }
}
