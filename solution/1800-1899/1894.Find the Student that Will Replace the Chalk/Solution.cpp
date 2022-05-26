class Solution {
public:
    int chalkReplacer(vector<int>& chalk, int k) {
        int n = chalk.size();
        vector<long long> s(n, chalk[0]);
        for (int i = 1; i < n; ++i) s[i] = s[i - 1] + chalk[i];
        k %= s[n - 1];
        return upper_bound(s.begin(), s.end(), k) - s.begin();
    }
};