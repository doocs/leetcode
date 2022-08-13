class Solution {
public:
    int brightestPosition(vector<vector<int>>& lights) {
        map<int, int> d;
        for (auto& e : lights) {
            int l = e[0] - e[1], r = e[0] + e[1];
            ++d[l];
            --d[r + 1];
        }
        int s = 0, mx = 0, ans = 0;
        for (auto& e : d) {
            s += e.second;
            if (s > mx) {
                mx = s;
                ans = e.first;
            }
        }
        return ans;
    }
};