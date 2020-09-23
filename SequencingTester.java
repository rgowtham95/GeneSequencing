import java.util.*;
public class SequencingTester
{
    public static boolean testIsPresent(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGCT", "TCGA", "AGCAGCAGCTTTTAGC", "AGCAGCAGCTTTTAGC", "AAAAAAAAAAAAA"};
        String [] searches = {"AGCT", "AGCT", "AGC", "AA", "AA" };
        boolean [] results = {true, false, true, false, true};
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            if(sequencer.isPresent(sequences[i], searches[i]) != results[i]){
                System.out.println("Fails for: " + sequences[i] + ", " + searches[i] + " != " + results[i]);
                pass = false;
            }
        }
        return pass;
    }
    
    public static boolean testGetPositions(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGCT", "TCGA", "AGCAGCAGCTTTTAGC", "AGCAGCAGCTTTTAGC", "AAAAAAAAAAAAA"};
        String [] searches = {"AGCT", "AGCT", "AGC", "AA", "AA" };
        Integer [][] results = { {0},
                                 {},
                                 {0, 3, 6, 13},
                                 {},
                                 {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11} };
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            ArrayList<Integer> output = sequencer.getPositions(sequences[i], searches[i]);
            ArrayList<Integer> nextResults = new ArrayList<Integer>(Arrays.asList(results[i]));
            System.out.println("i: " + i + ", output size: " + output.size() + ", nextResults size: " + nextResults.size());
            if(!areArrayListsTheSame(output, nextResults)){
                System.out.println("Fails for: " + sequences[i] + ", " + searches[i]);
                pass = false;
            }
        }
        return pass;
    }
    
    public static boolean areArrayListsTheSame(ArrayList<Integer> one, ArrayList<Integer> two){
        if(one.size() != two.size()){
            System.out.println("Fails at - size");
            return false;
        }
        for(int i = 0; i < one.size(); i++){
            if(!one.contains(two.get(i))){
                System.out.println("Fails at: " + i);
                return false;
            }
        }
        return true;
    }
    
    public static boolean testGetPairedSequence(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGCT", "TCGA", "AGCAGCAGCTTTTAGC", "AAAAAAAAAAAAA"};
        String [] pairs =  {"TCGA", "AGCT", "TCGTCGTCGAAAATCG", "TTTTTTTTTTTTT"};
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            if(!sequencer.getPairedSequence(sequences[i]).equals(pairs[i])){
                System.out.println("Fails for: " + sequences[i] + " does not match " + pairs[i]);
                pass = false;
            }
        }
        return pass;
    }
   
    public static boolean testReverseSequence(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGCT", "TCGA", "AGCAGCAGCTTTTAGC", "AAAAAAAAAAAAA"};
        String [] pairs =  {"TCGA", "AGCT", "CGATTTTCGACGACGA", "AAAAAAAAAAAAA"};
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            if(!sequencer.reverseSequence(sequences[i]).equals(pairs[i])){
                System.out.println("Fails for: " + sequences[i] + " does not match " + pairs[i]);
                pass = false;
            }
        }
        return pass;
    }
    
    public static boolean testGetAllPositionsLevel3(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGCT", "TCGA", "AGCAGCAGCTTTTCGA", "AGCAGCAGCTTTTAGC", "AAAAAAAAAAAAA", "ATATA", "GCGCGCGCG"};
        String [] searches = {"AGCT", "AGCT", "AGC", "AA", "AA", "AT", "TTTT" };
        Integer [][] results = { {0},
                                 {0},
                                 {0, 3, 6, 7, 12, 13},
                                 {9, 10, 11},
                                 {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                                 {0, 1, 2, 3},
                                 {}
                                };
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            ArrayList<Integer> output = sequencer.getAllPositions(sequences[i], searches[i]);
            ArrayList<Integer> nextResults = new ArrayList<Integer>(Arrays.asList(results[i]));
            System.out.println("i: " + i + ", output size: " + output.size() + ", nextResults size: " + nextResults.size());
            if(!areArrayListsTheSame(output, nextResults)){
                System.out.println("Fails for: " + sequences[i] + ", " + searches[i]);
                pass = false;
            }
        }
        return pass;
    }
    
    public static boolean testGetSmaller(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGAACT"};
        String [][] results = { { "GAACT", "AAACT", "AGACT", "AGAAT", "AGAAC"}
                          
                                };
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            ArrayList<String> output = sequencer.getSmaller(sequences[i]);
            ArrayList<String> nextResults = new ArrayList<String>(Arrays.asList(results[i]));
            System.out.println("i: " + i + ", output size: " + output.size() + ", nextResults size: " + nextResults.size());
            if(!areStringArrayListsTheSame(output, nextResults)){
                System.out.println("Fails for: " + sequences[i]);
                pass = false;
            }
        }
        return pass;
    } 
    
    public static boolean areStringArrayListsTheSame(ArrayList<String> one, ArrayList<String> two){
        if(one.size() != two.size()){
            System.out.println("Fails at - size");
            return false;
        }
        for(int i = 0; i < one.size(); i++){
            if(!one.contains(two.get(i))){
                System.out.println("Fails at: " + i);
                return false;
            }
        }
        return true;
    }
    
    public static boolean testGetLarger(){
        GeneSequencing sequencer = new GeneSequencing();
        String [] sequences = {"AGT"};
        String [][] results = { {"AAGT", "GAGT", "CAGT", "TAGT", "AGGT", "ACGT", "ATGT", "AGAT", "AGCT", "AGTT", "AGTA", "AGTG", "AGTC"},
                          
                                };
        boolean pass = true;
        for(int i = 0; i < sequences.length; i++){
            ArrayList<String> output = sequencer.getLarger(sequences[i]);
            ArrayList<String> nextResults = new ArrayList<String>(Arrays.asList(results[i]));
            System.out.println("i: " + i + ", output size: " + output.size() + ", nextResults size: " + nextResults.size());
            if(!areStringArrayListsTheSame(output, nextResults)){
                System.out.println("Fails for: " + sequences[i]);
                pass = false;
            }
        }
        return pass;
    }
    
   
}
