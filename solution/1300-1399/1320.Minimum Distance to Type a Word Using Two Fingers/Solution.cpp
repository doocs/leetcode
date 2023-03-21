class Solution {
public:
    int minimumDistance(string word) {
        int n = word.size();
        const int inf = 1 << 30;
        vector<vector<vector<int>>> f(n, vector<vector<int>>(26, vector<int>(26, inf)));
        for (int j = 0; j < 26; ++j) {
            f[0][word[0] - 'A'][j] = 0;
            f[0][j][word[0] - 'A'] = 0;
        }
        for (int i = 1; i < n; ++i) {
            int a = word[i - 1] - 'A';
            int b = word[i] - 'A';
            int d = dist(a, b);
            for (int j = 0; j < 26; ++j) {
                f[i][b][j] = min(f[i][b][j], f[i - 1][a][j] + d);
                f[i][j][b] = min(f[i][j][b], f[i - 1][j][a] + d);
                if (j == a) {
                    for (int k = 0; k < 26; ++k) {
                        int t = dist(k, b);
                        f[i][b][j] = min(f[i][b][j], f[i - 1][k][a] + t);
                        f[i][j][b] = min(f[i][j][b], f[i - 1][a][k] + t);
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 26; ++j) {
            ans = min(ans, f[n - 1][word[n - 1] - 'A'][j]);
            ans = min(ans, f[n - 1][j][word[n - 1] - 'A']);
        }
        return ans;
    }

    int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return abs(x1 - x2) + abs(y1 - y2);
    }
};