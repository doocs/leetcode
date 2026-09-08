class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            f[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (check(words[j], words[i])) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    private boolean check(String a, String b) {
        if (a.length() + 1 != b.length()) {
            return false;
        }
        int i = 0;
        for (int j = 0; j < b.length(); ++j) {
            if (i < a.length() && a.charAt(i) == b.charAt(j)) {
                ++i;
            }
        }
        return i == a.length();
    }
}
