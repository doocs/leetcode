class Solution {
public:
    int minDominoRotations(vector<int>& tops, vector<int>& bottoms) {
        int n = tops.size();
        auto f = [&](int x) {
            int cnt1 = 0, cnt2 = 0;
            for (int i = 0; i < n; ++i) {
                if (tops[i] != x && bottoms[i] != x) {
                    return n + 1;
                }
                cnt1 += tops[i] == x;
                cnt2 += bottoms[i] == x;
            }
            return n - max(cnt1, cnt2);
        };
        int ans = min(f(tops[0]), f(bottoms[0]));
        return ans > n ? -1 : ans;
    }
};