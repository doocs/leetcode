class Solution {
    public long countVowels(String word) {
        long ans = 0;
        for (int i = 0, n = word.length(); i < n; ++i) {
            char c = word.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                ans += (i + 1L) * (n - i);
            }
        }
        return ans;
    }
}