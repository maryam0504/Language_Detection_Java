package myProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class FindTheLanguage {

  public static void main(String[] args) {
    //input the name of txt for test
    Scanner input = new Scanner(System.in);
    String filePath = input.nextLine();
    
    //call methods from main
    
    String call = new Main().readUTF8File("Testing/" + filePath);
    
    ArrayList filterWords = new Main().extractWords(call, Locale.FRENCH);
    ArrayList<String> convertToBigrams = new Main().bigrams(filterWords);
    //compare the unknown txt
    compare(convertToBigrams);


  }

  public static void compare(ArrayList<String> target) {
    String[] fileName = {"Models/EnglishModel.txt", "Models/FrenchModel.txt",
        "Models/GermanModel.txt", "Models/ItalianModel.txt", "Models/SpanishModel.txt"};
    String[] theFileName = {"English", "French", "German", "Italian", "Spanish"};
    List<Sort> callSortResult = new ArrayList<Sort>();

    for (int i = 0; i < fileName.length; i++) {
      double prbapility = 1;
      ArrayList<String> fileContent = new ArrayList<String>();
      try {

        Reader reader = new InputStreamReader(new FileInputStream(fileName[i]), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
          fileContent.add(s);

        }
        for (int j = 0; j < target.size(); j++) {
          prbapility =
              prbapility * binarySearchBigrams(fileContent, fileContent.size(), target.get(j));

        }
        bufferedReader.close();
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      callSortResult.add(new Sort(theFileName[i], prbapility));



      

    }
    Collections.sort(callSortResult);
    
    for(int b = 0; b < callSortResult.size(); b++) {
      System.out.printf("%s : %s \n", callSortResult.get(b).theFileName, callSortResult.get(b).prbapility);
    }
    
    
    for(int a = 0; a  < 1; a++){
           
      System.out.printf("Identefied language : %s %s", callSortResult.get(a).theFileName, callSortResult.get(a).prbapility) ;
    }
    


  }

  public static double binarySearchBigrams(ArrayList<String> arr, int last, String target) {
    int mid; // index of the midpoint
    int first = 0;
    String[] midvalue; // object that is assigned arr[mid]
    int origLast = last; // save original value of last
    while (first < last) { // test for nonempty sublist
      mid = (first + last) / 2;
      String str = arr.get(mid);
      midvalue = str.split(" ");
      String midvaluepat = midvalue[0];
      if (midvaluepat.equals(target)) {
        return Double.parseDouble(midvalue[1]); // have a match
      } else if (target.compareTo(midvaluepat) < 0) {
        last = mid; // search lower sublist. Reset last
      } else {
        first = mid + 1; // search upper sublist. Reset first
      }
    }
    return 0; // target not found�
  }

}

