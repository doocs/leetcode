import java.util.*;

public class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        // Step 1: Frequency counter for each character in the string
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Collect all characters with at least k frequency
        List<Character> candidates = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            if (entry.getValue() >= k) {
                candidates.add(entry.getKey());
            }
        }

        // Step 4: Breadth-First Search (BFS) to generate subsequences
        Queue<String> queue = new LinkedList<>();
        queue.offer(""); // Start with an empty sequence
        String longest = "";

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            // Try appending each candidate character to the current subsequence
            for (char ch : candidates) {
                String newSubseq = curr + ch;

                // Check if the new subsequence is repeatable k times
                if (canBeRepeatedKTimes(newSubseq, s, k)) {
                    queue.offer(newSubseq);

                    // Update longest if newSubseq is longer or lexicographically larger
                    if (newSubseq.length() > longest.length() || 
                        (newSubseq.length() == longest.length() && newSubseq.compareTo(longest) > 0)) {
                        longest = newSubseq;
                    }
                }
            }
        }

        return longest;
    }

    // Helper function to check if a subsequence can be repeated k times
    private boolean canBeRepeatedKTimes(String subseq, String s, int k) {
        int i = 0, count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == subseq.charAt(i)) {
                i++;
                if (i == subseq.length()) {
                    i = 0;
                    count++;
                    if (count == k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1
        String s1 = "letsleetcode";
        int k1 = 2;
        System.out.println(sol.longestSubsequenceRepeatedK(s1, k1)); // Output: "let"

        // Test case 2
        String s2 = "bb";
        int k2 = 2;
        System.out.println(sol.longestSubsequenceRepeatedK(s2, k2)); // Output: "b"

        // Test case 3
        String s3 = "ab";
        int k3 = 2;
        System.out.println(sol.longestSubsequenceRepeatedK(s3, k3)); // Output: ""

        // Test case 4
        String s4 = "bbabbabbbbabaababab";
        int k4 = 3;
        System.out.println(sol.longestSubsequenceRepeatedK(s4, k4)); // Output: "bbbb"
    }
}
