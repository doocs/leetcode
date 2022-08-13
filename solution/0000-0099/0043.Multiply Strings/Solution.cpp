class Solution {
public:
    string multiply(string num1, string num2) {
        int m = num1.size(), n = num2.size();
        vector<int> res(m + n);
        for (int i = 0; i < n; ++i) {
            int b = num2[n - 1 - i] - '0';
            mul(num1, b, i, res);
        }
        string ans = "";
        for (int v : res) ans.push_back(v + '0');
        while (ans.size() > 1 && ans.back() == '0') ans.pop_back();
        reverse(ans.begin(), ans.end());
        return ans;
    }

    void mul(string A, int b, int i, vector<int>& res) {
        for (int j = A.size() - 1, t = 0; j >= 0 || t > 0; --j) {
            if (j >= 0) {
                int a = A[j] - '0';
                t += a * b;
            }
            res[i++] += t % 10;
            if (res[i - 1] >= 10) {
                res[i - 1] %= 10;
                ++res[i];
            }
            t /= 10;
        }
    }
};