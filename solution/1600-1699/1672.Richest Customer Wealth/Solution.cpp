class Solution {
public:
    int maximumWealth(vector<vector<int>>& accounts) {
        int res = 0;
        for (auto& account : accounts) {
            int t = 0;
            for (auto& money : account) {
                t += money;
            }
            res = max(res, t);
        }
        return res;
    }
};