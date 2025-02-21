function minimumWhiteTiles(floor: string, numCarpets: number, carpetLen: number): number {
    const n = floor.length;
    const f: number[][] = Array.from({ length: n }, () => Array(numCarpets + 1).fill(-1));
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + (floor[i] === '1' ? 1 : 0);
    }
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return 0;
        }
        if (j === 0) {
            return s[n] - s[i];
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        if (s[i + 1] === s[i]) {
            return dfs(i + 1, j);
        }
        const ans = Math.min(1 + dfs(i + 1, j), dfs(i + carpetLen, j - 1));
        f[i][j] = ans;
        return ans;
    };
    return dfs(0, numCarpets);
}
