import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        
        // Step 1: Sort the products lexicographically
        Arrays.sort(products);
        
        int left = 0;
        int right = products.length - 1;
        
        // Step 2: Iterate through each character of the search word
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            
            // Narrow down the left window pointer
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }
            
            // Narrow down the right window pointer
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }
            
            // Step 3: Collect up to 3 lexicographically smallest suggestions
            List<String> currentSuggestions = new ArrayList<>();
            for (int j = 0; j < 3 && left + j <= right; j++) {
                currentSuggestions.add(products[left + j]);
            }
            
            result.add(currentSuggestions);
        }
        
        return result;
    }
}
