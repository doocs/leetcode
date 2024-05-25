class Solution {
public:
    vector<double> dicesProbability(int n) {
        vector<int> f(7, 1);
        f[0] = 0;
        for (int i = 2; i <= n; ++i) {
            vector<int> g(6 * i + 1, 0);
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j - k >= 0 && j - k < f.size()) {
                        g[j] += f[j - k];
                    }
                }
            }
            f = move(g);
        }
        double m = pow(6, n);
        vector<double> ans;
        for (int j = n; j <= 6 * n; ++j) {
            ans.push_back(f[j] / m);
        }
        return ans;
    }
};