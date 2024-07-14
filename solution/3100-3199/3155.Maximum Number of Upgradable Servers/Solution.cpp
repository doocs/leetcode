class Solution {
public:
    vector<int> maxUpgrades(vector<int>& count, vector<int>& upgrade, vector<int>& sell, vector<int>& money) {
        int n = count.size();
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            ans.push_back(min(count[i], (int) ((1LL * count[i] * sell[i] + money[i]) / (upgrade[i] + sell[i]))));
        }
        return ans;
    }
};