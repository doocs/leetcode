class Solution {
public:
    int brightestPosition(vector<vector<int>>& lights) {
        map<int, int> d;
        for (auto& x : lights) {
            int l = x[0] - x[1], r = x[0] + x[1];
            ++d[l];
            --d[r + 1];
        }
        int ans = 0, s = 0, mx = 0;
        for (auto& [i, v] : d) {
            s += v;
            if (mx < s) {
                mx = s;
                ans = i;
            }
        }
        return ans;
    }
};