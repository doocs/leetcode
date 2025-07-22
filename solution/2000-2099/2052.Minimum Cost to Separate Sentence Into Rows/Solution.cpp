class Solution {
public:
    int minimumCost(string sentence, int k) {
        istringstream iss(sentence);
        vector<int> s = {0};
        string w;
        while (iss >> w) {
            s.push_back(s.back() + w.size());
        }
        int n = s.size() - 1;
        int f[n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (s[n] - s[i] + n - i - 1 <= k) {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int ans = INT_MAX;
            for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
                int m = s[j] - s[i] + j - i - 1;
                ans = min(ans, dfs(j) + (k - m) * (k - m));
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};
