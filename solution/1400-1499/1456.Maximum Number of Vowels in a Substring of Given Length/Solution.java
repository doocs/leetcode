class Solution {
    public int maxVowels(String s, int k) {
        int t = 0, n = s.length();
        for (int i = 0; i < k; ++i) {
            if (isVowel(s.charAt(i))) {
                ++t;
            }
        }
        int ans = t;
        for (int i = k; i < n; ++i) {
            if (isVowel(s.charAt(i))) {
                ++t;
            }
            if (isVowel(s.charAt(i - k))) {
                --t;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}