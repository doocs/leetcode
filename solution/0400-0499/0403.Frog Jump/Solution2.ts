function canCross(stones: number[]): boolean {
    const n = stones.length;
    const f: boolean[][] = new Array(n).fill(0).map(() => new Array(n).fill(false));
    f[0][0] = true;
    for (let i = 1; i < n; ++i) {
        for (let j = i - 1; j >= 0; --j) {
            const k = stones[i] - stones[j];
            if (k - 1 > j) {
                break;
            }
            f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
            if (i == n - 1 && f[i][k]) {
                return true;
            }
        }
    }
    return false;
}
