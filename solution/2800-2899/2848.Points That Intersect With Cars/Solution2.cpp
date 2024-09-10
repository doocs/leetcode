class Solution {
public:
    int numberOfPoints(vector<vector<int>>& nums) {
        map<int, int> d;
        for (const auto& e : nums) {
            int start = e[0], end = e[1];
            ++d[start];
            --d[end + 1];
        }
        int ans = 0, s = 0, last = 0;
        for (const auto& [cur, v] : d) {
            if (s > 0) {
                ans += cur - last;
            }
            s += v;
            last = cur;
        }
        return ans;
    }
};
