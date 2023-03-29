class Solution {
public:
    bool canDistribute(vector<int>& nums, vector<int>& quantity) {
        int m = quantity.size();
        int s[1 << m];
        memset(s, 0, sizeof(s));
        for (int i = 1; i < 1 << m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i >> j & 1) {
                    s[i] = s[i ^ (1 << j)] + quantity[j];
                    break;
                }
            }
        }
        unordered_map<int, int> cnt;
        for (int& x : nums) {
            ++cnt[x];
        }
        int n = cnt.size();
        vector<int> arr;
        for (auto& [_, x] : cnt) {
            arr.push_back(x);
        }
        bool f[n][1 << m];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][0] = true;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < 1 << m; ++j) {
                if (i && f[i - 1][j]) {
                    f[i][j] = true;
                    continue;
                }
                for (int k = j; k; k = (k - 1) & j) {
                    bool ok1 = i == 0 ? j == k : f[i - 1][j ^ k];
                    bool ok2 = s[k] <= arr[i];
                    if (ok1 && ok2) {
                        f[i][j] = true;
                        break;
                    }
                }
            }
        }
        return f[n - 1][(1 << m) - 1];
    }
};