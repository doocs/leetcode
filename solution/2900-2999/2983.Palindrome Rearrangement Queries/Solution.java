class Solution {
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length();
        int m = n / 2;
        String t = new StringBuilder(s.substring(m)).reverse().toString();
        s = s.substring(0, m);
        int[][] pre1 = new int[m + 1][0];
        int[][] pre2 = new int[m + 1][0];
        int[] diff = new int[m + 1];
        pre1[0] = new int[26];
        pre2[0] = new int[26];
        for (int i = 1; i <= m; ++i) {
            pre1[i] = pre1[i - 1].clone();
            pre2[i] = pre2[i - 1].clone();
            ++pre1[i][s.charAt(i - 1) - 'a'];
            ++pre2[i][t.charAt(i - 1) - 'a'];
            diff[i] = diff[i - 1] + (s.charAt(i - 1) == t.charAt(i - 1) ? 0 : 1);
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int[] q = queries[i];
            int a = q[0], b = q[1];
            int c = n - 1 - q[3], d = n - 1 - q[2];
            ans[i] = a <= c ? check(pre1, pre2, diff, a, b, c, d)
                            : check(pre2, pre1, diff, c, d, a, b);
        }
        return ans;
    }

    private boolean check(int[][] pre1, int[][] pre2, int[] diff, int a, int b, int c, int d) {
        if (diff[a] > 0 || diff[diff.length - 1] - diff[Math.max(b, d) + 1] > 0) {
            return false;
        }
        if (d <= b) {
            return Arrays.equals(count(pre1, a, b), count(pre2, a, b));
        }
        if (b < c) {
            return diff[c] - diff[b + 1] == 0 && Arrays.equals(count(pre1, a, b), count(pre2, a, b))
                && Arrays.equals(count(pre1, c, d), count(pre2, c, d));
        }
        int[] cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1));
        int[] cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d));
        return cnt1 != null && cnt2 != null && Arrays.equals(cnt1, cnt2);
    }

    private int[] count(int[][] pre, int i, int j) {
        int[] cnt = new int[26];
        for (int k = 0; k < 26; ++k) {
            cnt[k] = pre[j + 1][k] - pre[i][k];
        }
        return cnt;
    }

    private int[] sub(int[] cnt1, int[] cnt2) {
        int[] cnt = new int[26];
        for (int i = 0; i < 26; ++i) {
            cnt[i] = cnt1[i] - cnt2[i];
            if (cnt[i] < 0) {
                return null;
            }
        }
        return cnt;
    }
}