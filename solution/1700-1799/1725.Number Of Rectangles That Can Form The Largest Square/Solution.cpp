class Solution {
public:
    int countGoodRectangles(vector<vector<int>>& rectangles) {
        int ans = 0, mx = 0;
        for (auto& r : rectangles) {
            int t = min(r[0], r[1]);
            if (mx < t) {
                mx = t;
                ans = 1;
            } else if (mx == t)
                ++ans;
        }
        return ans;
    }
};