class Solution {
public:
    int maxTotalReward(vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        int n = rewardValues.size();
        int f[rewardValues.back() << 1];
        memset(f, -1, sizeof(f));
        function<int(int)> dfs = [&](int x) {
            if (f[x] != -1) {
                return f[x];
            }
            auto it = upper_bound(rewardValues.begin(), rewardValues.end(), x);
            int ans = 0;
            for (; it != rewardValues.end(); ++it) {
                ans = max(ans, rewardValues[it - rewardValues.begin()] + dfs(x + *it));
            }
            return f[x] = ans;
        };
        return dfs(0);
    }
};