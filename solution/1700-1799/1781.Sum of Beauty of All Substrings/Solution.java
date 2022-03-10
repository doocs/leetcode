class Solution {
    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int[] counter = new int[26];
            for (int j = i; j < n; ++j) {
                ++counter[s.charAt(j) - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v > 0) {
                        mi = Math.min(mi, v);
                        mx = Math.max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
}