class Solution {
public:
    int maxDistance(string s, int k) {
        auto calc = [&](char a, char b) {
            int ans = 0, mx = 0, cnt = 0;
            for (char c : s) {
                if (c == a || c == b) {
                    ++mx;
                } else if (cnt < k) {
                    ++mx;
                    ++cnt;
                } else {
                    --mx;
                }
                ans = max(ans, mx);
            }
            return ans;
        };
        int a = calc('S', 'E');
        int b = calc('S', 'W');
        int c = calc('N', 'E');
        int d = calc('N', 'W');
        return max({a, b, c, d});
    }
};
