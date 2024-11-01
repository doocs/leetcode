class Solution {
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long f = energyDrinkA[0], g = energyDrinkB[0];
        for (int i = 1; i < n; ++i) {
            long ff = Math.max(f + energyDrinkA[i], g);
            g = Math.max(g + energyDrinkB[i], f);
            f = ff;
        }
        return Math.max(f, g);
    }
}
