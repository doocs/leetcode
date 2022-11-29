class Solution {
    public String largestMerge(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < m && j < n) {
            boolean gt = word1.substring(i).compareTo(word2.substring(j)) > 0;
            ans.append(gt ? word1.charAt(i++) : word2.charAt(j++));
        }
        ans.append(word1.substring(i));
        ans.append(word2.substring(j));
        return ans.toString();
    }
}