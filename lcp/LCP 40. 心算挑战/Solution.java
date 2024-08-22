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
        final int inf = 1 << 29;
        int mx1 = -inf, mx2 = -inf;
        for (int i = 0; i < n - cnt; ++i) {
            if (cards[i] % 2 == 1) {
                mx1 = cards[i];
            } else {
                mx2 = cards[i];
            }
        }
        int mi1 = inf, mi2 = inf;
        for (int i = n - 1; i >= n - cnt; --i) {
            if (cards[i] % 2 == 1) {
                mi2 = cards[i];
            } else {
                mi1 = cards[i];
            }
        }
        ans = Math.max(ans - mi1 + mx1, ans - mi2 + mx2);
        return ans < 0 ? 0 : ans;
    }
}
