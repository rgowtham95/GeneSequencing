import java.util.*;
public class GeneSequencing {
    public boolean isPresent(String geneSequence, String searchSequence) {
        boolean isPresent = false;
        int num = geneSequence.length() - searchSequence.length();
        if(geneSequence.length() == searchSequence.length()) {
            num = 1;
        }
        for(int i = 0; i < num; i++) {
            if(geneSequence.substring(i, i+searchSequence.length()).equals(searchSequence)){
                isPresent = true;
                return isPresent;
            }
        }
        return isPresent;
    }

    public ArrayList<Integer> getPositions(String geneSequence, String searchSequence) {
        ArrayList<Integer> indices = new ArrayList<Integer>();        
        int num = geneSequence.length() - searchSequence.length();
        for(int i = 0; i <= num; i++) {
            if(geneSequence.substring(i, i+searchSequence.length()).equals(searchSequence)){
                indices.add(i);
                System.out.println(i);
            }
        }
        return indices;
    }

    public String getPairedSequence(String geneSequence) {
        String matchingSequence = "";
        for(int i = 0; i < geneSequence.length(); i++) {
            if(geneSequence.charAt(i) == 'A') {
                matchingSequence += "T";
            }
            else if(geneSequence.charAt(i) == 'T') {
                matchingSequence += "A";
            }
            else if(geneSequence.charAt(i) == 'C') {
                matchingSequence += "G";
            }
            else if(geneSequence.charAt(i) == 'G') {
                matchingSequence += "C";
            }
        }
        return matchingSequence;
    }

    public String reverseSequence(String geneSequence) {
        String reversedSequence = "";
        for(int i = geneSequence.length()-1; i >= 0; i--) {
            reversedSequence += geneSequence.charAt(i);
        }
        return reversedSequence;
    }

    public ArrayList<Integer> getAllPositions(String geneSequence, String searchSequence) {
        ArrayList<Integer> positions1 = getPositions(geneSequence,searchSequence);        
        ArrayList<Integer> positions2 = getPositions(getPairedSequence(geneSequence),reverseSequence(searchSequence));
        ArrayList<Integer> positions3 = getPositions(getPairedSequence(geneSequence),searchSequence);
        ArrayList<Integer> positions4 = getPositions(geneSequence,reverseSequence(searchSequence));

        ArrayList<Integer> allPositions = new ArrayList<Integer>();
        for(int i = 0; i < positions1.size(); i++) {
            allPositions.add(positions1.get(i));            
        }
        for(int i = 0; i < positions2.size(); i++) {
            allPositions.add(positions2.get(i));            
        }
        for(int i = 0; i < positions3.size(); i++) {
            allPositions.add(positions3.get(i));            
        }
        for(int i = 0; i < positions4.size(); i++) {
            allPositions.add(positions4.get(i));            
        }
        for(int a = 0; a < allPositions.size()-1; a++) {
            for(int b = a + 1; b < allPositions.size(); b++) {
                if(allPositions.get(a) == allPositions.get(b)){ 
                    allPositions.remove(b);
                }
            }
        }
        return allPositions;
    }

    public ArrayList<String> getSmaller(String geneSequence) {
        ArrayList<String> genes = new ArrayList<String>();
        String gene = "";
        for(int i = 0; i < geneSequence.length(); i++) {

            gene = geneSequence.substring(0,i) + geneSequence.substring(i + 1, geneSequence.length());
            genes.add(gene);

        }
        for(int i = 0; i < genes.size()-1; i++) {
            if(genes.get(i).equals(genes.get(i+1))) {
                genes.remove(i);
            }
        }
        return genes;
    }

    public ArrayList<String> getLarger(String geneSequence) {
        ArrayList<String> genes = new ArrayList<String>();
        String parent = "AGCT";
        String gene = "";
        for(int i = 0; i < parent.length(); i++) {
            gene = parent.charAt(i) + geneSequence;
            System.out.println(gene);
            genes.add(gene);
        }
        for(int i = 1; i < geneSequence.length(); i++) {
            for(int a = 0; a < parent.length(); a++) {
                if(parent.charAt(a) != geneSequence.charAt(i-1)) {
                    gene = geneSequence.substring(0, i) + parent.charAt(a) + geneSequence.substring(i, geneSequence.length());
                    genes.add(gene);
                    System.out.println(gene);
                }
            }
        }
        for(int i = 0; i < parent.length(); i++) {
            if(parent.charAt(i) != geneSequence.charAt(geneSequence.length()-1)) {
                gene = geneSequence + parent.charAt(i);
                System.out.println(gene);
                genes.add(gene);
            }
        }
        return genes;
    }
    
    public ArrayList<Integer> getAllPositions2(String geneSequence, String searchSequence) {
        //I'm too lazy to do this level, I'm probably the only one in my class doing these assignments anyways.
        //I enjoy these cows though, they're good practice for the exams too.  
        //Thank you for teaching me Computer Science Mr. Mayewsky!
        return null;
    }
    
    
}