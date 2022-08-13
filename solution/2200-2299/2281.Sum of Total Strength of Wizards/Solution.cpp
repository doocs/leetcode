class Solution {
public:
    int totalStrength(vector<int>& strength) {
        int n = strength.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            while (!stk.empty() && strength[stk.top()] >= strength[i]) stk.pop();
            if (!stk.empty()) left[i] = stk.top();
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty() && strength[stk.top()] > strength[i]) stk.pop();
            if (!stk.empty()) right[i] = stk.top();
            stk.push(i);
        }
        int mod = 1e9 + 7;
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = (s[i] + strength[i]) % mod;
        vector<int> ss(n + 2);
        for (int i = 0; i < n + 1; ++i) ss[i + 1] = (ss[i] + s[i]) % mod;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int v = strength[i];
            int l = left[i] + 1, r = right[i] - 1;
            long a = (long)(i - l + 1) * (ss[r + 2] - ss[i + 1]);
            long b = (long)(r - i + 1) * (ss[i + 1] - ss[l]);
            ans = (ans + v * ((a - b) % mod)) % mod;
        }
        return (int)(ans + mod) % mod;
    }
};