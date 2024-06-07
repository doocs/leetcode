function missingRolls(rolls: number[], mean: number, n: number): number[] {
    const m = rolls.length;
    const s = (n + m) * mean - rolls.reduce((a, b) => a + b, 0);
    if (s > n * 6 || s < n) {
        return [];
    }
    const ans: number[] = Array(n).fill((s / n) | 0);
    for (let i = 0; i < s % n; ++i) {
        ans[i]++;
    }
    return ans;
}
