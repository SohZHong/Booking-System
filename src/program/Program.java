package assignment;

import java.io.IOException;
import java.text.ParseException;
import utils.UI;

public class Program {

    private final ProgramWindow programWindow;
    private final ProgramPanel programPanel;
    private final UI ui;
    
    //Variables to be called across system
    // Width and height divided by 32 to define image count. E.g. 1280/32 = 40 images wide
    public final static int SYSTEM_HEIGHT = 720;
    public final static int SYSTEM_WIDTH = 1280;
    
    public Program() throws ParseException, InterruptedException, IOException {
        
        ui = new UI();
        
        ui.loadUI();
        
        programPanel = new ProgramPanel();
        programWindow = new ProgramWindow();
        
    }

}
