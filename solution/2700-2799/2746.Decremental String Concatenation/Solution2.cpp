class Solution {
public:
    int minimizeConcatenatedLength(vector<string>& words) {
        int n = words.size();
        vector<vector<vector<int>>> f(n + 1, vector<vector<int>>(26, vector<int>(26)));
        for (int i = n - 1; i > 0; --i) {
            auto& s = words[i];
            int m = s.size();
            int c = s[0] - 'a';
            int d = s[m - 1] - 'a';
            for (int a = 0; a < 26; ++a) {
                for (int b = 0; b < 26; ++b) {
                    int x = f[i + 1][a][d] - (c == b);
                    int y = f[i + 1][c][b] - (d == a);
                    f[i][a][b] = m + min(x, y);
                }
            }
        }
        int a = words[0].front() - 'a';
        int b = words[0].back() - 'a';
        return words[0].size() + f[1][a][b];
    }
};
