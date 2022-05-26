class Solution {
public:
    vector<int> missingRolls(vector<int>& rolls, int mean, int n) {
        int m = rolls.size();
        int s = (n + m) * mean;
        for (int& v : rolls) s -= v;
        if (s > n * 6 || s < n) return {};
        vector<int> ans(n, s / n);
        for (int i = 0; i < s % n; ++i) ++ans[i];
        return ans;
    }
};