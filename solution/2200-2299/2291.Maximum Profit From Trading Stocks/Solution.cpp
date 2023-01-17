class Solution {
public:
    int maximumProfit(vector<int>& present, vector<int>& future, int budget) {
        int n = present.size();
        int f[budget + 1];
        memset(f, 0, sizeof f);
        for (int i = 0; i < n; ++i) {
            int a = present[i], b = future[i];
            for (int j = budget; j >= a; --j) {
                f[j] = max(f[j], f[j - a] + b - a);
            }
        }
        return f[budget];
    }
};