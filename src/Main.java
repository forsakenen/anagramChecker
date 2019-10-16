import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File fileName = new File("eventyr.txt");

        List<String> wordList = new ArrayList<>(); // List of all the words from the file
        List<String> anagramList = new ArrayList<>(); // Saving the words that are anagrams for printing

        // Reading from file
        try {
            BufferedReader read = new BufferedReader(new FileReader(fileName));
            String line;

            // Putting all the words into a list.
            while((line = read.readLine()) != null) {
                wordList.add(line);
            }

            read.close();

        } catch (Exception e) {
            System.err.format("Exception while reading file '%s", fileName);
            e.printStackTrace();
        }

        // Defining char arrays, for easier comparisons
        char[] w1;
        char[] w2;

        Boolean foundAnagram;

        for (int i = 0; i <= wordList.size() - 2; i++) {
            foundAnagram = false; // Presetting bool every new loop
            anagramList.clear(); // Clearing words

            w1 = wordList.get(i).toCharArray(); // Saving first word as chararray
            String firstWord = new String(w1);


            for(int j = i + 1; j <= wordList.size() - 1; j++) {
                w2 = wordList.get(j).toCharArray(); // Saving second word as array
                String secondWord = new String(w2);

                // If words aren't same length or already checked, we can go to next word.
                if(w1.length != w2.length) {
                    continue;
                }

                // Sorting arrays alphabetically before we compare them.
                Arrays.sort(w1);
                Arrays.sort(w2);

                // Comparing the sorted arrays, and storing the result as a bool.
                foundAnagram = Arrays.equals(w1, w2);

                if(foundAnagram) {
                    // Adding found words to anagram list
                    anagramList.add(firstWord);
                    anagramList.add(secondWord);
                    wordList.remove(secondWord);
                }
            }

            // Section to format the output as requested.

            String output = "";
            if(anagramList.size() > 0) {

                for(String anagrams : anagramList) {
                    output += anagrams + " ";
                }
                // Printing out the list of anagrams
                System.out.println(output);
            }
        }
    }
}
