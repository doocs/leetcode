function unhappyFriends(n: number, preferences: number[][], pairs: number[][]): number {
    const d: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n - 1; ++j) {
            d[i][preferences[i][j]] = j;
        }
    }
    const p: number[] = Array(n).fill(0);
    for (const [x, y] of pairs) {
        p[x] = y;
        p[y] = x;
    }
    let ans = 0;
    for (let x = 0; x < n; ++x) {
        const y = p[x];
        for (let i = 0; i < d[x][y]; ++i) {
            const u = preferences[x][i];
            const v = p[u];
            if (d[u][x] < d[u][v]) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
