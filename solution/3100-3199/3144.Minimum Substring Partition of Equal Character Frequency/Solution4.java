class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int[] cnt = new int[26];
            Map<Integer, Integer> freq = new HashMap<>(26);
            int ans = n - i;
            for (int j = i; j < n; ++j) {
                int k = cs[j] - 'a';
                if (cnt[k] > 0) {
                    if (freq.merge(cnt[k], -1, Integer::sum) == 0) {
                        freq.remove(cnt[k]);
                    }
                }
                ++cnt[k];
                freq.merge(cnt[k], 1, Integer::sum);
                if (freq.size() == 1) {
                    ans = Math.min(ans, 1 + f[j + 1]);
                }
            }
            f[i] = ans;
        }
        return f[0];
    }
}
