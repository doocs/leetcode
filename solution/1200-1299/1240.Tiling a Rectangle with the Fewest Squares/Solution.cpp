class Solution {
public:
    int tilingRectangle(int n, int m) {
        memset(filled, 0, sizeof(filled));
        this->n = n;
        this->m = m;
        ans = n * m;
        dfs(0, 0, 0);
        return ans;
    }

private:
    int filled[13];
    int n, m;
    int ans;

    void dfs(int i, int j, int t) {
        if (j == m) {
            ++i;
            j = 0;
        }
        if (i == n) {
            ans = t;
            return;
        }
        if (filled[i] >> j & 1) {
            dfs(i, j + 1, t);
        } else if (t + 1 < ans) {
            int r = 0, c = 0;
            for (int k = i; k < n; ++k) {
                if (filled[k] >> j & 1) {
                    break;
                }
                ++r;
            }
            for (int k = j; k < m; ++k) {
                if (filled[i] >> k & 1) {
                    break;
                }
                ++c;
            }
            int mx = min(r, c);
            for (int w = 1; w <= mx; ++w) {
                for (int k = 0; k < w; ++k) {
                    filled[i + w - 1] |= 1 << (j + k);
                    filled[i + k] |= 1 << (j + w - 1);
                }
                dfs(i, j + w, t + 1);
            }
            for (int x = i; x < i + mx; ++x) {
                for (int y = j; y < j + mx; ++y) {
                    filled[x] ^= 1 << y;
                }
            }
        }
    }
};