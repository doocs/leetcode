class Solution {
public:
    string largestMultipleOfThree(vector<int>& digits) {
        sort(digits.begin(), digits.end());
        int n = digits.size();
        int f[n + 1][3];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - digits[i - 1] % 3 + 3) % 3] + 1);
            }
        }
        if (f[n][0] <= 0) {
            return "";
        }
        string ans;
        for (int i = n, j = 0; i; --i) {
            int k = (j - digits[i - 1] % 3 + 3) % 3;
            if (f[i - 1][k] + 1 == f[i][j]) {
                ans += digits[i - 1] + '0';
                j = k;
            }
        }
        int i = 0;
        while (i < ans.size() - 1 && ans[i] == '0') {
            ++i;
        }
        return ans.substr(i);
    }
};