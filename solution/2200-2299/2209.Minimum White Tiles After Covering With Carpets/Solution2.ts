function minimumWhiteTiles(floor: string, numCarpets: number, carpetLen: number): number {
    const n = floor.length;
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + (floor[i] === '1' ? 1 : 0);
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(numCarpets + 1).fill(0));
    for (let i = n - 1; i >= 0; --i) {
        for (let j = 0; j <= numCarpets; ++j) {
            if (floor[i] === '0') {
                f[i][j] = f[i + 1][j];
            } else if (j === 0) {
                f[i][j] = s[n] - s[i];
            } else {
                const cover = i + carpetLen <= n ? f[i + carpetLen][j - 1] : 0;
                f[i][j] = Math.min(1 + f[i + 1][j], cover);
            }
        }
    }
    return f[0][numCarpets];
}
