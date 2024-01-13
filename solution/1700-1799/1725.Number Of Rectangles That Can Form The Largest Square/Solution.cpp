class Solution {
public:
    int countGoodRectangles(vector<vector<int>>& rectangles) {
        int ans = 0, mx = 0;
        for (auto& e : rectangles) {
            int x = min(e[0], e[1]);
            if (mx < x) {
                mx = x;
                ans = 1;
            } else if (mx == x) {
                ++ans;
            }
        }
        return ans;
    }
};