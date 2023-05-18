package assignment;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;

public class ProgramWindow extends JFrame{
    
    private final ProgramPanel programPanel;
    
    public ProgramWindow() throws ParseException, InterruptedException, IOException{
        
        programPanel = new ProgramPanel();
        
        //JFrame doesn't exit by default when clicking X, therefore change operation
        setDefaultCloseOperation(3); //JFrame.EXIT_ON_CLOSE has int value of 3, which closes the application when clicking X
        
        add(programPanel);
        
        setLocationRelativeTo(null); //Center GUI to window
        
        setResizable(false); //Disable resize to prevent glitches
        
        setTitle("Hotel Booking System"); //Setting title of program
        
        pack(); //Fit Window Size to its component/panel, validate the container and set it to it's natural size.
        
        centerProgram(); //Center Program to Host Screen
        
        setVisible(true); //JFrame Window is invisible by default, its placement must always be at bottom
        
    }

    private void centerProgram() {

        //Getting screen size of host
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX, centerY;

        centerX = (int) (screen.getWidth()/2) - getWidth()/2;
        centerY = (int) (screen.getHeight()/2) - getHeight()/2;

        setLocation(centerX, centerY);
    }
    
}
