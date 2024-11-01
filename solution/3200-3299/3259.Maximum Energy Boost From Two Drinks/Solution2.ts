function maxEnergyBoost(energyDrinkA: number[], energyDrinkB: number[]): number {
    const n = energyDrinkA.length;
    let [f, g] = [energyDrinkA[0], energyDrinkB[0]];
    for (let i = 1; i < n; ++i) {
        [f, g] = [Math.max(f + energyDrinkA[i], g), Math.max(g + energyDrinkB[i], f)];
    }
    return Math.max(f, g);
}
