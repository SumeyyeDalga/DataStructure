import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SpellChecker {

    private static final int MAX_OPC = 10000;


    public static GTUList<String> generateEditDistance1(String input, GTUHashSet<String> dictionary, int[] operationCount) {
        GTUList<String> variants = new GTUArrayList<>();
    
        for (int i = 0; i <= input.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                operationCount[0]++;
                if (operationCount[0] > MAX_OPC) {
                    return variants;
                }
    
                // Insertion
                StringBuilder newWordBuilder = new StringBuilder(input);
                newWordBuilder.insert(i, c);
                String newWord = newWordBuilder.toString();
                if (!variants.contains(newWord)) {
                    variants.add(newWord);
                }
    
                // Replacement
                if (i < input.length()) {
                    StringBuilder replaceBuilder = new StringBuilder(input);
                    replaceBuilder.setCharAt(i, c);
                    String replace = replaceBuilder.toString();
                    if (!variants.contains(replace)) {
                        variants.add(replace);
                    }
                }
            }
    
            // Deletion
            if (i < input.length()) {
                operationCount[0]++;
                if (operationCount[0] > MAX_OPC) {
                    return variants;
                }
    
                StringBuilder deletedBuilder = new StringBuilder(input);
                deletedBuilder.deleteCharAt(i);
                String deletedWord = deletedBuilder.toString();
                if (!variants.contains(deletedWord)) {
                    variants.add(deletedWord);
                }
            }
        }
    
        return variants;
    }
    
    
    
    public static GTUList<String> generateEditDistance2Variants(String word, GTUHashSet<String> dictionary) {
        int[] operationCount = {0}; // Counter will be passed by reference
        GTUList<String> variants = new GTUArrayList<>();
        GTUHashSet<String> suggestion_set = new GTUHashSet<>(); //for optimization because of contains metjod of GTUHashSet is O(1) but GTUArrayList is O(n)

    
        GTUList<String> distance1Variants = generateEditDistance1(word, dictionary, operationCount);
    
        for (int i = 0; i < distance1Variants.size(); i++) {
            if (operationCount[0] > MAX_OPC){
                System.out.println("Maximum operations reached.");
                break;
            }
    
            String variant = distance1Variants.get(i);
            if (dictionary.contains(variant) && !suggestion_set.contains(variant)) {
                variants.add(variant);
                suggestion_set.add(variant);
            }
    
            GTUList<String> distance2Variants = generateEditDistance1(variant, dictionary, operationCount);
            for (int j = 0; j < distance2Variants.size(); j++) {
                if (operationCount[0] > MAX_OPC){
                    break;
                }
    
                String distance2Variant = distance2Variants.get(j);
                if (dictionary.contains(distance2Variant) && !suggestion_set.contains(distance2Variant)) {
                    variants.add(distance2Variant);
                    suggestion_set.add(distance2Variant);
                }
            }
        }
    
        return variants;
    }
    
    
    
    
    public static void main(String[] args) throws IOException {
        GTUHashSet<String> dictionary = new GTUHashSet<>();
        
        BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
        String word;
        
        while ((word = reader.readLine()) != null) {
            dictionary.add(word.trim());
        }
        reader.close();
    
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine().trim().toLowerCase();
        
        if (input.isEmpty() || !input.matches("[a-zA-Z]+")) {
            System.out.println("Please enter a valid word.");
            return;
        }
        
        long startTime = System.nanoTime();
        int count = 0;
        
        if (dictionary.contains(input)) {
            System.out.println("Correct.");
        } else {
            System.out.println("Incorrect.");
            System.out.println("Suggestions: ");
            
            GTUList<String> suggestions = generateEditDistance2Variants(input, dictionary);
            for (int i = 0; i < suggestions.size(); i++) {
                System.out.println(suggestions.get(i));
                count++;
            }
            
            System.out.println("Total suggestions: " + count);
            long endTime = System.nanoTime();
            System.out.printf("Lookup and suggestion took %.2f ms\n", (endTime - startTime) / 1e6);
        }
    }
}
