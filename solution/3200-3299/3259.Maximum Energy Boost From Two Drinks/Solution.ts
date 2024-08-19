function maxEnergyBoost(energyDrinkA: number[], energyDrinkB: number[]): number {
    const n = energyDrinkA.length;
    const f: number[][] = Array.from({ length: n }, () => [0, 0]);
    f[0][0] = energyDrinkA[0];
    f[0][1] = energyDrinkB[0];
    for (let i = 1; i < n; i++) {
        f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
        f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
    }
    return Math.max(...f[n - 1]!);
}
