package myProject;

public class Sort implements Comparable<Sort> {
  
  String theFileName;
  double prbapility;


  
    public Sort(String theFileName, double prbapility) {
       
       this.theFileName = theFileName;
       this.prbapility = prbapility;
     
   }
   @Override
   public int compareTo(Sort s) {
     if (this.prbapility == s.prbapility) {
       return this.theFileName.compareTo(s.theFileName);
     } else {
       return (s.prbapility - this.prbapility) > 0 ? 1 : -1;
     }
   
    }

}
