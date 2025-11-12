class Solution {
public:
    int distinctPoints(string s, int k) {
        int n = s.size();
        vector<int> f(n + 1), g(n + 1);
        int x = 0, y = 0;
        for (int i = 1; i <= n; ++i) {
            char c = s[i - 1];
            if (c == 'U')
                ++y;
            else if (c == 'D')
                --y;
            else if (c == 'L')
                --x;
            else
                ++x;
            f[i] = x;
            g[i] = y;
        }
        unordered_set<long long> st;
        for (int i = k; i <= n; ++i) {
            int a = f[n] - (f[i] - f[i - k]);
            int b = g[n] - (g[i] - g[i - k]);
            st.insert(1LL * a * n + b);
        }
        return st.size();
    }
};
