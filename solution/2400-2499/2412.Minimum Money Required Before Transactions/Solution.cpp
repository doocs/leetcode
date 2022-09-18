class Solution {
public:
    long long minimumMoney(vector<vector<int>>& transactions) {
        long long s = 0, ans = 0;
        for (auto& e : transactions) {
            s += max(0, e[0] - e[1]);
        }
        for (auto& e : transactions) {
            if (e[0] > e[1]) {
                ans = max(ans, s + e[1]);
            } else {
                ans = max(ans, s + e[0]);
            }
        }
        return ans;
    }
};