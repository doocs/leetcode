class Solution {
public:
    long long maxEnergyBoost(vector<int>& energyDrinkA, vector<int>& energyDrinkB) {
        int n = energyDrinkA.size();
        long long f = energyDrinkA[0], g = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            long long ff = max(f + energyDrinkA[i], g);
            g = max(g + energyDrinkB[i], f);
            f = ff;
        }
        return max(f, g);
    }
};
