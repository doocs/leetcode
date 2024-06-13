class Solution {
public:
    int maxTotalReward(vector<int>& rewardValues) {
        sort(rewardValues.begin(), rewardValues.end());
        rewardValues.erase(unique(rewardValues.begin(), rewardValues.end()), rewardValues.end());
        int n = rewardValues.size();
        int m = rewardValues.back() << 1;
        bool f[m];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int v : rewardValues) {
            for (int j = 1; j < m; ++j) {
                if (0 <= j - v && j - v < v) {
                    f[j] = f[j] || f[j - v];
                }
            }
        }
        int ans = m - 1;
        while (!f[ans]) {
            --ans;
        }
        return ans;
    }
};