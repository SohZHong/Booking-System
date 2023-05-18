package components;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utils.LoadImage;
import static utils.LoadImage.COMPANY_LOGO;

public class LogoDisplay extends JPanel {

    private JLabel logoLabel;
    private ImageIcon logo;
    private int width, height;
    
    public LogoDisplay(int imgWidth, int imgHeight){
        
        this.width = imgWidth;
        this.height = imgHeight;
        
        logo = new ImageIcon(LoadImage.getScaledImage(COMPANY_LOGO, width, height));
        
        //Making image a swing component
        logoLabel = new JLabel(logo);
        
        this.setLayout(new BorderLayout());
        this.add(logoLabel, BorderLayout.CENTER);
    }
    
}
