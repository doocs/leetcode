function maxScore(n: number, k: number, stayScore: number[][], travelScore: number[][]): number {
    const f: number[][] = Array.from({ length: k + 1 }, () => Array(n).fill(-Infinity));
    f[0].fill(0);
    for (let i = 1; i <= k; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let h = 0; h < n; ++h) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i - 1][h] + (j == h ? stayScore[i - 1][j] : travelScore[h][j]),
                );
            }
        }
    }
    return Math.max(...f[k]);
}
