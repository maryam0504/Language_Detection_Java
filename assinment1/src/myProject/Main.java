package myProject;

import java.util.*;
import java.text.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Arrays;

public class Main {

  public static void Main(String[] args) {
    // TODO Auto-generated method stub
    
    // proccess Frensh lang
    String result=readUTF8File("Learning/French.txt");
    ArrayList words=new ArrayList();
    words = extractWords(result, Locale.FRENCH);
    ArrayList convertWords = bigrams(words);
    String countProbabilities = sortCountBigrams(convertWords);
    outputWordsToUTF8File("Models/FrenchModel.txt", countProbabilities);
    
    
 // proccess English lang
    String result2=readUTF8File("Learning/English.txt");
    ArrayList words2=new ArrayList();
    words2 = extractWords(result2, Locale.ENGLISH);
    ArrayList convertWords2 = bigrams(words2);
    String countProbabilities2 = sortCountBigrams(convertWords2);
    outputWordsToUTF8File("Models/EnglishModel.txt", countProbabilities2);
    
    
 // proccess German lang
    String result3=readUTF8File("Learning/German.txt");
    ArrayList words3=new ArrayList();
    words3 = extractWords(result3, Locale.GERMAN);
    ArrayList convertWords3 = bigrams(words3);
    String countProbabilities3 = sortCountBigrams(convertWords3);
    outputWordsToUTF8File("Models/GermanModel.txt", countProbabilities3);
    
    
 // proccess Italian lang
    String result4=readUTF8File("Learning/Italian.txt");
    ArrayList words4=new ArrayList();
    words4 = extractWords(result4, Locale.ITALIAN);
    ArrayList convertWords4 = bigrams(words4);
    String countProbabilities4 = sortCountBigrams(convertWords4);
    outputWordsToUTF8File("Models/ItalianModel.txt", countProbabilities4);
    
    
 // proccess spanish lang
    String result5=readUTF8File("Learning/SPANISH.txt");
    ArrayList words5=new ArrayList();
    words5 = extractWords(result5, new Locale("es"));
    ArrayList convertWords5 = bigrams(words5);
    String countProbabilities5 = sortCountBigrams(convertWords5);
    outputWordsToUTF8File("Models/SpanishModel.txt", countProbabilities5);
             


  }
  
  //this method to read input files
  public static String readUTF8File(String filePath) {
    StringBuilder content = new StringBuilder();
    try {
        Reader reader = new InputStreamReader(
                new FileInputStream(filePath), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
          content.append(" "+s + "\n");
        }
        bufferedReader.close();
        reader.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return content.toString();
} 
  
  
  
  //this methoe to extract just the lowercase chracter
  public static ArrayList extractWords(String inputText, Locale currentLocale) {
    ArrayList wordList = new ArrayList();
    BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);

    wordIterator.setText(inputText);
    int start = wordIterator.first();
    int end = wordIterator.next();
    while (end != BreakIterator.DONE) {
        String word = inputText.substring(start, end);
        word = word.toLowerCase();
        if (Character.isLetter(word.charAt(0)) && word.length() > 1) {
            wordList.add(word);
        }
        start = end;
        end = wordIterator.next();
    }
    return wordList;
}
  
  //this method to convert word to bigram
  public static ArrayList bigrams(ArrayList wordList){
    ArrayList mBigarm = new ArrayList();

    for (int i = 0; i < wordList.size(); i++) {
        String word = wordList.get(i).toString();

        for(int g = 0; g<word.length() - 1; g++){
          mBigarm.add(word.substring(g, g + 2));
        }
        
    }
    mBigarm.add("?");
    return mBigarm;

}
//this method to sort bigram and calc probabbility
public static String sortCountBigrams(ArrayList<String> theBigrams){
    Collections.sort(theBigrams); // sort all bigrams
    String curr = theBigrams.get(0).toString(); // get the first bigram
    StringBuilder sorrt = new StringBuilder();
    int counter = 0;
    double total = theBigrams.size();
    for(int i = 0; i < theBigrams.size() - 1; i++){
      if(theBigrams.get(i + 1).equals(theBigrams.get(i))){
          counter++;
      }else{
        sorrt.append( theBigrams.get(i) + " " + (double) (counter/total));
        sorrt.append("\n");
          counter = 1; // reset counter to 1
      }
    }  
         
    

    return sorrt.toString();
}

//this method to output txt file in Models folder
public static void outputWordsToUTF8File(String filePath, String sorrt){
  try{
      Writer writer = new OutputStreamWriter(
              new FileOutputStream(filePath), StandardCharsets.UTF_8);
      PrintWriter printWriter = new PrintWriter(writer);
      
      writer.write(sorrt);
      writer.close();
      printWriter.close();

  }catch (Exception e){
      e.printStackTrace();
  }
}
  
  

}
