class Solution {
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String w : words) {
            if (s.startsWith(w)) {
                ++ans;
            }
        }
        return ans;
    }
}