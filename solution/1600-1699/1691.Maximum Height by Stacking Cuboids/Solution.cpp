class Solution {
public:
    int maxHeight(vector<vector<int>>& cuboids) {
        for (auto& c : cuboids) sort(c.begin(), c.end());
        sort(cuboids.begin(), cuboids.end());
        int n = cuboids.size();
        vector<int> f(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    f[i] = max(f[i], f[j]);
                }
            }
            f[i] += cuboids[i][2];
        }
        return *max_element(f.begin(), f.end());
    }
};