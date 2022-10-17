class Solution {
public:
    string shortestSuperstring(vector<string>& words) {
        int n = words.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            auto a = words[i];
            for (int j = 0; j < n; ++j) {
                auto b = words[j];
                if (i != j) {
                    for (int k = min(a.size(), b.size()); k > 0; --k) {
                        if (a.substr(a.size() - k) == b.substr(0, k)) {
                            g[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        vector<vector<int>> dp(1 << n, vector<int>(n));
        vector<vector<int>> p(1 << n, vector<int>(n, -1));
        for (int i = 0; i < 1 << n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j) & 1) {
                    int pi = i ^ (1 << j);
                    for (int k = 0; k < n; ++k) {
                        if ((pi >> k) & 1) {
                            int v = dp[pi][k] + g[k][j];
                            if (v > dp[i][j]) {
                                dp[i][j] = v;
                                p[i][j] = k;
                            }
                        }
                    }
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[(1 << n) - 1][i] > dp[(1 << n) - 1][j]) {
                j = i;
            }
        }
        vector<int> arr = {j};
        for (int i = (1 << n) - 1; p[i][j] != -1;) {
            int k = i;
            i ^= (1 << j);
            j = p[k][j];
            arr.push_back(j);
        }
        unordered_set<int> vis(arr.begin(), arr.end());
        for (int i = 0; i < n; ++i) {
            if (!vis.count(i)) {
                arr.push_back(i);
            }
        }
        reverse(arr.begin(), arr.end());
        string ans = words[arr[0]];
        for (int i = 1; i < n; ++i) {
            int k = g[arr[i - 1]][arr[i]];
            ans += words[arr[i]].substr(k);
        }
        return ans;
    }
};