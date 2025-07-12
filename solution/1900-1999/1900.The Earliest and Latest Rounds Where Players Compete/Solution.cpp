int f[30][30][31];
class Solution {
public:
    vector<int> earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        return dfs(firstPlayer - 1, secondPlayer - 1, n);
    }

private:
    vector<int> dfs(int l, int r, int n) {
        if (f[l][r][n] != 0) {
            return decode(f[l][r][n]);
        }
        if (l + r == n - 1) {
            f[l][r][n] = encode(1, 1);
            return {1, 1};
        }

        int min = INT_MAX, max = INT_MIN;
        int m = n >> 1;

        for (int i = 0; i < (1 << m); i++) {
            vector<bool> win(n, false);
            for (int j = 0; j < m; j++) {
                if ((i >> j) & 1) {
                    win[j] = true;
                } else {
                    win[n - 1 - j] = true;
                }
            }
            if (n & 1) {
                win[m] = true;
            }

            win[n - 1 - l] = false;
            win[n - 1 - r] = false;
            win[l] = true;
            win[r] = true;

            int a = 0, b = 0, c = 0;
            for (int j = 0; j < n; j++) {
                if (j == l) a = c;
                if (j == r) b = c;
                if (win[j]) c++;
            }

            vector<int> t = dfs(a, b, c);
            min = std::min(min, t[0] + 1);
            max = std::max(max, t[1] + 1);
        }

        f[l][r][n] = encode(min, max);
        return {min, max};
    }

    int encode(int x, int y) {
        return (x << 8) | y;
    }

    vector<int> decode(int val) {
        return {val >> 8, val & 255};
    }
};