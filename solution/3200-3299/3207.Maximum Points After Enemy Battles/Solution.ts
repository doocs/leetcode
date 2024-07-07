function maximumPoints(enemyEnergies: number[], currentEnergy: number): number {
    enemyEnergies.sort((a, b) => a - b);
    if (currentEnergy < enemyEnergies[0]) {
        return 0;
    }
    let ans = 0;
    for (let i = enemyEnergies.length - 1; ~i; --i) {
        ans += Math.floor(currentEnergy / enemyEnergies[0]);
        currentEnergy %= enemyEnergies[0];
        currentEnergy += enemyEnergies[i];
    }
    return ans;
}
