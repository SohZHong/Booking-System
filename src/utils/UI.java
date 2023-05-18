package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIManager;

public class UI {
    
    private static final Color backgroundColor = new Color(255, 255, 255);
    private static final Color buttonColor = new Color(51, 157, 255);
    private static final Color fontColor = new Color(51, 0, 0);
            
    private static final Font mainFont = new Font("STXihei", Font.PLAIN, 15);
    private static final Font inputFont = new Font("STXihei", Font.PLAIN, 15);
    private static final Font buttonFont = new Font("STXihei", Font.PLAIN, 15);
    
    
    public final void loadUI(){
        try{
            //Setting Windows Native look to UI
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        UIManager.getLookAndFeelDefaults().put("Panel.background", backgroundColor);
        
        UIManager.getLookAndFeelDefaults().put("Label.font", mainFont);
        
        UIManager.getLookAndFeelDefaults().put("Button.font", buttonFont);
        UIManager.getLookAndFeelDefaults().put("Button.border", backgroundColor);
        UIManager.getLookAndFeelDefaults().put("Button.background", buttonColor);
        UIManager.getLookAndFeelDefaults().put("Button.highlight", buttonColor.brighter());
        UIManager.getLookAndFeelDefaults().put("Button.light", buttonColor.brighter().brighter());
        UIManager.getLookAndFeelDefaults().put("Button.shadow", buttonColor.brighter());
        UIManager.getLookAndFeelDefaults().put("Button.darkShadow", buttonColor.darker().darker());
        UIManager.getLookAndFeelDefaults().put("Button.margin", new Insets(10, 20, 10, 20));

        UIManager.getLookAndFeelDefaults().put("TextField.background", backgroundColor);
        UIManager.getLookAndFeelDefaults().put("TextField.font", inputFont);
        UIManager.getLookAndFeelDefaults().put("TextField.foreground", fontColor); 
        UIManager.getLookAndFeelDefaults().put("TextField.margin", new Insets(0, 2, 0, 2));
        UIManager.getLookAndFeelDefaults().put("TextField.padding", new Insets(0, 2, 0, 2));
       
        
        UIManager.getLookAndFeelDefaults().put("PasswordField.background", backgroundColor);
        UIManager.getLookAndFeelDefaults().put("PasswordField.font", inputFont);
        UIManager.getLookAndFeelDefaults().put("PasswordField.foreground", inputFont); 
        UIManager.getLookAndFeelDefaults().put("TextField.margin", new Insets(0, 2, 0, 2));
        UIManager.getLookAndFeelDefaults().put("TextField.padding", new Insets(0, 2, 0, 2));
        
        UIManager.getLookAndFeelDefaults().put("Table.rowHeight", 30);
        UIManager.getLookAndFeelDefaults().put("Table.font", mainFont);
        UIManager.getLookAndFeelDefaults().put("Table.foreground", fontColor);
        UIManager.getLookAndFeelDefaults().put("TableHeader.font", mainFont);
        UIManager.getLookAndFeelDefaults().put("TableHeader.foreground", fontColor);


    }
    
}
