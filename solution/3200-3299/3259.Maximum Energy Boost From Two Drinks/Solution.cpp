class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        vector<vector<long long>> f(n, vector<long long>(2));
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            f[i][0] = max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return max(f[n - 1][0], f[n - 1][1]);
    }
};
