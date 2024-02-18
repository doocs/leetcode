class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int ans = 0;
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            String s = words[i];
            for (int j = i + 1; j < n; ++j) {
                String t = words[j];
                if (t.startsWith(s) && t.endsWith(s)) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}