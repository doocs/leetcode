class Solution {
public:
    long long minTime(vector<int>& skill, vector<int>& mana) {
        int n = skill.size();
        vector<long long> f(n);
        for (int x : mana) {
            long long tot = 0;
            for (int i = 0; i < n; ++i) {
                tot = max(tot, f[i]) + 1LL * skill[i] * x;
            }
            f[n - 1] = tot;
            for (int i = n - 2; i >= 0; --i) {
                f[i] = f[i + 1] - 1LL * skill[i + 1] * x;
            }
        }
        return f[n - 1];
    }
};
