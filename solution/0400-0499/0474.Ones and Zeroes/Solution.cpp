class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (auto& s : strs) {
            auto [a, b] = count(s);
            for (int i = m; i >= a; --i) {
                for (int j = n; j >= b; --j) {
                    f[i][j] = max(f[i][j], f[i - a][j - b] + 1);
                }
            }
        }
        return f[m][n];
    }

    pair<int, int> count(string& s) {
        int a = count_if(s.begin(), s.end(), [](char c) { return c == '0'; });
        return {a, s.size() - a};
    }
};