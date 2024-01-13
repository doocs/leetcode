class Solution {
    public int beautySum(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[26];
            Map<Integer, Integer> freq = new HashMap<>();
            int mi = 1, mx = 1;
            for (int j = i; j < n; ++j) {
                int k = s.charAt(j) - 'a';
                freq.merge(cnt[k], -1, Integer::sum);
                ++cnt[k];
                freq.merge(cnt[k], 1, Integer::sum);

                if (cnt[k] == 1) {
                    mi = 1;
                }
                if (freq.getOrDefault(mi, 0) == 0) {
                    ++mi;
                }
                if (cnt[k] > mx) {
                    mx = cnt[k];
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
}