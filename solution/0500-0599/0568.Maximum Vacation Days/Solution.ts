function maxVacationDays(flights: number[][], days: number[][]): number {
    const n = flights.length;
    const K = days[0].length;
    const inf = Number.NEGATIVE_INFINITY;
    const f: number[][] = Array.from({ length: K + 1 }, () => Array(n).fill(inf));
    f[0][0] = 0;
    for (let k = 1; k <= K; k++) {
        for (let j = 0; j < n; j++) {
            f[k][j] = f[k - 1][j];
            for (let i = 0; i < n; i++) {
                if (flights[i][j]) {
                    f[k][j] = Math.max(f[k][j], f[k - 1][i]);
                }
            }
            f[k][j] += days[j][k - 1];
        }
    }
    return Math.max(...f[K]);
}
