class Solution {
    public int maxSubstringLength(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int j = s.charAt(i) - 'a';
            if (first[j] == -1) {
                first[j] = i;
            }
            last[j] = i;
        }
        int ans = -1;
        for (int k = 0; k < 26; ++k) {
            int i = first[k];
            if (i == -1) {
                continue;
            }
            int mx = last[k];
            for (int j = i; j < n; ++j) {
                int a = first[s.charAt(j) - 'a'];
                int b = last[s.charAt(j) - 'a'];
                if (a < i) {
                    break;
                }
                mx = Math.max(mx, b);
                if (mx == j && j - i + 1 < n) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}