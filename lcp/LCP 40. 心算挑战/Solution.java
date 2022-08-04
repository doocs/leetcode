class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        int n = cards.length;
        for (int i = 0; i < cnt; ++i) {
            ans += cards[n - i - 1];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        int inf = 0x3f3f3f3f;
        int a = inf, b = inf;
        for (int i = 0; i < cnt; ++i) {
            int v = cards[n - i - 1];
            if (v % 2 == 1) {
                a = Math.min(a, v);
            } else {
                b = Math.min(b, v);
            }
        }
        int c = -inf, d = -inf;
        for (int i = cnt; i < n; ++i) {
            int v = cards[n - i - 1];
            if (v % 2 == 0) {
                c = Math.max(c, v);
            } else {
                d = Math.max(d, v);
            }
        }
        return Math.max(0, Math.max(ans - a + c, ans - b + d));
    }
}