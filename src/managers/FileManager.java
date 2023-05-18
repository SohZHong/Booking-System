package managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {
    //double backquote to prevent compiler interpreting words as special characters, eg. \n
    private static final String TEXT_FOLDER = new File("resources\\data\\").getAbsolutePath();
    
    private String filePath;
    private String[] content;
    
    public FileManager(String fileName){
        //Ensuring all enquiries redirected to correct folder
        this.filePath = TEXT_FOLDER + File.separator + fileName + ".txt"; //File system only accepts txt format
        //Assigning Array length of 0
        //0 returns no results but null value causes exceptions when looped
        content = new String[0];
    }
    
    //Create files at designated location
    public void createFile() throws IOException{
        File newFile = new File(filePath);
        newFile.createNewFile();
    }
    
    //Truncate file of its contents
    public void clearFile() {
        try(PrintWriter writer = new PrintWriter(filePath)){
            writer.print("");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    //Method necessary as reading in constructor causes error before writing
    public void readFile() throws IOException{
        String data = "";
        File file = new File(filePath);
        //Scanner over BufferedReader as system only handles small txt files
        Scanner reader = new Scanner(file);
        //Reading all lines of the text file
        while(reader.hasNextLine()){
            data += reader.nextLine() + "\n"; //new line after finishing a line
        }
        reader.close();
        //Adding all data into array with each line as an array item
        content = data.split("\n");
    }
    
    public String[] readLine(int lineNumber){
        return content[lineNumber].split(", "); //Array Item is separated by comma
    }
    
    public int contentLength(){
        //Get length of number of lines
        return content.length;
    }
    
    public void writeFile(String[] data, boolean append) throws IOException{
        try(FileWriter writer = new FileWriter(filePath, append)){
            String line = String.join(", ", data);
            writer.write(line + "\n");
            writer.flush(); //Flush all data to be written to disk, also saves the program from closing to update data
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
