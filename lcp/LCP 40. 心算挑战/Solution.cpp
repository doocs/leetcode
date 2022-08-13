class Solution {
public:
    int maxmiumScore(vector<int>& cards, int cnt) {
        sort(cards.begin(), cards.end());
        reverse(cards.begin(), cards.end());
        int ans = 0, n = cards.size();
        for (int i = 0; i < cnt; ++i) ans += cards[i];
        if (ans % 2 == 0) return ans;
        int inf = 0x3f3f3f3f;
        int a = inf, b = inf, c = -inf, d = -inf;
        for (int i = 0; i < cnt; ++i) {
            int v = cards[i];
            if (v % 2 == 1)
                a = min(a, v);
            else
                b = min(b, v);
        }
        for (int i = cnt; i < n; ++i) {
            int v = cards[i];
            if (v % 2 == 0)
                c = max(c, v);
            else
                d = max(d, v);
        }
        return max(0, max(ans - a + c, ans - b + d));
    }
};