class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        vector<int> f(n + 1);
        for (int i = n - 1; i >= 0; --i) {
            int ans = INT_MIN;
            int s = 0;
            for (int j = i; j < i + 3 && j < n; ++j) {
                s += stoneValue[j];
                ans = max(ans, s - f[j + 1]);
            }
            f[i] = ans;
        }
        int res = f[0];
        if (res == 0) {
            return "Tie";
        }
        return res > 0 ? "Alice" : "Bob";
    }
};
