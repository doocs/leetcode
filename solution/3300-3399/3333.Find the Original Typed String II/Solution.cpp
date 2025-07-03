class Solution {
public:
    int possibleStringCount(string word, int k) {
        const int mod = 1e9 + 7;
        vector<int> nums;
        long long ans = 1;
        int cur = 0;
        int n = word.size();

        for (int i = 0; i < n; ++i) {
            cur++;
            if (i == n - 1 || word[i] != word[i + 1]) {
                if (cur > 1) {
                    if (k > 0) {
                        nums.push_back(cur - 1);
                    }
                    ans = ans * cur % mod;
                }
                cur = 0;
                k--;
            }
        }

        if (k < 1) {
            return ans;
        }

        int m = nums.size();
        vector<vector<int>> f(m + 1, vector<int>(k, 0));
        f[0][0] = 1;

        for (int i = 1; i <= m; ++i) {
            int x = nums[i - 1];
            vector<long long> s(k + 1, 0);
            for (int j = 0; j < k; ++j) {
                s[j + 1] = (s[j] + f[i - 1][j]) % mod;
            }
            for (int j = 0; j < k; ++j) {
                int l = max(0, j - x);
                f[i][j] = (s[j + 1] - s[l] + mod) % mod;
            }
        }

        long long sum = 0;
        for (int j = 0; j < k; ++j) {
            sum = (sum + f[m][j]) % mod;
        }

        return (ans - sum + mod) % mod;
    }
};
