class Solution {
    public int possibleStringCount(String word, int k) {
        final int mod = (int) 1e9 + 7;
        List<Integer> nums = new ArrayList<>();
        long ans = 1;
        int cur = 0;
        int n = word.length();

        for (int i = 0; i < n; i++) {
            cur++;
            if (i == n - 1 || word.charAt(i) != word.charAt(i + 1)) {
                if (cur > 1) {
                    if (k > 0) {
                        nums.add(cur - 1);
                    }
                    ans = ans * cur % mod;
                }
                cur = 0;
                k--;
            }
        }

        if (k < 1) {
            return (int) ans;
        }

        int m = nums.size();
        int[][] f = new int[m + 1][k];
        f[0][0] = 1;

        for (int i = 1; i <= m; i++) {
            int x = nums.get(i - 1);
            long[] s = new long[k + 1];
            for (int j = 0; j < k; j++) {
                s[j + 1] = (s[j] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j < k; j++) {
                int l = Math.max(0, j - x);
                f[i][j] = (int) ((s[j + 1] - s[l] + mod) % mod);
            }
        }

        long sum = 0;
        for (int j = 0; j < k; j++) {
            sum = (sum + f[m][j]) % mod;
        }

        return (int) ((ans - sum + mod) % mod);
    }
}
