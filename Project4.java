/*
Description: THIS IS THE MAIN FILE. The project intends to use the Queue data 
structure that is derived from doubly linked list, in order to store characters
and strings, that will make it easier to identify whether or not a line of text 
is a palindrome or not. 
 */

package project.pkg4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Character.toLowerCase;

/**
 *
 * @author antho
 */
public class Project4 {
    
    /*
    An instance of Queue was created in the field in order to store all of the lines of text that were determined to be palindromes. 
    In the readFile method, I'm going to read each line of text, if it is determined that the line of text is a palindrome, then, I will
    store the lines of text in plainsQueue in order to write them to a file later on.
    */
    
    private Queue palinsQueue;
    
    
    /*
    Function: readFile(String path)
    Description: This method will read a text file from the file path that it is passed in its parameters. 
    As it reads each line of text, it calls the isPalindrome method, and if the line of text is determined to
    be a palindrome, then that line of text is enqueued to the field queue for follow on methods. 
    Inputs: A file path is required
    Outputs: The method itself returns nothing, it will however enqueue Strings as required. 
    */
    
    public void readFile(String path){
            
            FileReader fr = null;
            BufferedReader br = null;
    
            try{
                
                fr  = new FileReader(path);
                br = new BufferedReader(fr);
                String line;
                palinsQueue = new Queue();
                
                while((line = br.readLine()) != null){
                    
                    if(isPalindrome(line)){
                        palinsQueue.enqueue(line);
                    }
                    
                } 
                    
            }
            catch(Exception e){
                System.out.println("Error opening file");
                 }
            finally{
                try{
                    fr.close();
                    br.close();
                }
                catch(Exception e){
                System.out.println(e.getMessage());
                }
            }
    }
    
    
    /*
    Function: writeFile(String path)
    Description: This method will write to the file path passed. Any data saved in palinsQueue, it will 
    dequeue and write the string to file.
    Inputs: A file path is required
    Outputs: The method itself returns nothing, it will however dequeue every String from palinsQueue 
    and write those strings to whatever file path passed. 
    */
    
    public void writeFile(String path){

        BufferedWriter bw = null;
        FileWriter fw = null;

        try 
        { 
          fw = new FileWriter(path);
          bw = new BufferedWriter(fw);
          String line;
          
          while(!palinsQueue.isEmpty()){
              
            line = (String)palinsQueue.dequeue();
              
            bw.write(line);
            bw.newLine();
              
          }
          
        } 
        catch (Exception e) 
        { 
            System.err.println("Couldn't open file"); 
        }
        finally{
            try{
                
                bw.close();       
                fw.close();
               
            }
            catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }
    
    }
    
    /*
    Function: boolean isPalindrome(String testString)
    Description: This method will take a string as input, parse each character from 
    the string, and then enqueue each character to a Queue. After it has parsed every 
    character, it will then place each character (which at this point will be lower case)
    into a string in a certain format that will enable a comparison between the line 
    read normally and the line read in reverse. If determined to be a palindrome, then 
    the method will return true, else the method will return false. 
    Inputs: A string that will be tested to see if it is a palindrome
    Outputs: A boolean statement of either true or false
    */
    
    public boolean isPalindrome(String testString){
        
        String regular = "";
        String reverse = "";
        Queue queueReg = new Queue();
        Queue queueReverse = new Queue();
        
        
        for(int i = 0; i < testString.length(); i++){
                if(testString.charAt(i)!= ' ')
                      queueReg.enqueue(toLowerCase(testString.charAt(i)));
        }
                    
        for(int i = testString.length() - 1; i >= 0; i-- ){
                if(testString.charAt(i)!= ' ')
                      queueReverse.enqueue(toLowerCase(testString.charAt(i)));
        }
                    
        while(!queueReg.isEmpty()){
                regular += (char) queueReg.dequeue();
                reverse += (char) queueReverse.dequeue();
        }
        
        return regular.equals(reverse);
        
    }

    
    public static void main(String[] args) {
        
        /*
        The test code below simply creates an instance of the project in order to 
        call two methods that will allow for us to read a file given a file path, 
        and write to a file given a file path. The file path that you see in the 
        methods parameters is a file path that is specific to my computer. I essentially 
        read from a text file called pal.txt and if I find any palindromes, then 
        I call the writeFile method of the project in order to write all of the 
        palindromes that I found to the from the file that I read. Please ensure 
        to enter file paths applicable to you. I also added a System.out.println
        in the test code to make sure that it works on any palindrome. Remember, 
        the isPalindrome method returns a boolean, true if it is a palindrome, and 
        false if it is not. 
        */
        
        Project4 test = new Project4();
        
        
        //Test out any other palindromes that you can think of.
        //System.out.println(test.isPalindrome("baby baby"));
        
        test.readFile("C:\\Users\\antho\\Desktop\\testcode.txt");
        test.writeFile("C:\\Users\\antho\\Desktop\\pal.txt");
       
        
    }
    
}
