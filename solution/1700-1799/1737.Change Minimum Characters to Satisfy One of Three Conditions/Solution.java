class Solution {
    private int ans;

    public int minCharacters(String a, String b) {
        int m = a.length(), n = b.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt1[a.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            ++cnt2[b.charAt(i) - 'a'];
        }
        ans = m + n;
        for (int i = 0; i < 26; ++i) {
            ans = Math.min(ans, m + n - cnt1[i] - cnt2[i]);
        }
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }

    private void f(int[] cnt1, int[] cnt2) {
        for (int i = 1; i < 26; ++i) {
            int t = 0;
            for (int j = i; j < 26; ++j) {
                t += cnt1[j];
            }
            for (int j = 0; j < i; ++j) {
                t += cnt2[j];
            }
            ans = Math.min(ans, t);
        }
    }
}