public class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum exceeds the maximum possible sum, it's impossible
        if (sum > 9 * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // Greedily append as many 9s as possible
        int numNines = sum / 9;
        for (int i = 0; i < numNines; i++) {
            sb.append('9');
        }

        // Calculate the remaining digit needed if sum is not a perfect multiple of 9
        int remainder = sum % 9;
        if (remainder > 0) {
            sb.append(remainder);
        }

        // Fill the remaining digit spots with zeros to maintain exactly 'num' digits
        while (sb.length() < num) {
            sb.append('0');
        }

        return sb.toString();
    }
}
