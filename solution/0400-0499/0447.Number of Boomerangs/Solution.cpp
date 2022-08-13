class Solution {
public:
    int numberOfBoomerangs(vector<vector<int>>& points) {
        int ans = 0;
        for (const auto& p : points) {
            unordered_map<int, int> cnt;
            for (const auto& q : points) {
                ++cnt[(p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1])];
            }
            for (const auto& [_, v] : cnt) {
                ans += v * (v - 1);
            }
        }
        return ans;
    }
};
