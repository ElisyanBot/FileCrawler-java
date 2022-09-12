package filecrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class FileCrawler {
  Scanner readLine = new Scanner(System.in);

  FileCrawler() {
   
    System.out.println("what do you want to search for?");
    String userAnswer = readLine.nextLine();
    System.out.println("Files that includes your matches: ");
    getFiles( System.getProperty("user.dir"), userAnswer);
    
 }
  
  void searchWord(String searchWord, String fileName) {
    File searchedFile = new File(fileName);
    try {
      Scanner scanner = new Scanner(searchedFile);
      while(scanner.hasNextLine()){
        if(null != scanner.findInLine(searchWord)){
          System.out.println(fileName);
          break;
        }
        scanner.nextLine(); 
      }
      scanner.close();
    } catch( FileNotFoundException e ) {
      System.err.println("File Can't be opend for scanning:  "  + searchedFile.getAbsolutePath());
    } catch (NoSuchElementException e) {
      e.getStackTrace();
    }
  }
  
  //gets all file from explorer
  void getFiles(String startDir, String userSearch ){
   String[] childDirs = new File(startDir).list();
   if (childDirs != null) {
    for(String file : childDirs) {
      if(!file.contains(".")) {
        this.getFiles(startDir + "/" + file, userSearch);
      } else {
        this.searchWord( userSearch, startDir + "/" + file);
      }
    }
   }
  }
}