class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        int d[110]{};
        for (auto& e : nums) {
            d[e[0]]++;
            d[e[1] + 1]--;
        }
        int ans = 0, s = 0;
        for (int x : d) {
            s += x;
            ans += s > 0;
        }
        return ans;
    }
};