function extractMantra(matrix: string[], mantra: string): number {
    const [m, n, l] = [matrix.length, matrix[0].length, mantra.length];
    const q: number[][] = [[0, 0, 0]];
    const vis: boolean[][][] = Array(m)
        .fill(0)
        .map(() =>
            Array(n)
                .fill(0)
                .map(() => Array(l + 1).fill(false)),
        );
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let ans = 0;
    for (; q.length > 0; ++ans) {
        for (let size = q.length; size > 0; --size) {
            const [i, j, k] = q.shift()!;
            if (k === l) {
                return ans;
            }
            if (matrix[i][j] === mantra[k] && !vis[i][j][k + 1]) {
                vis[i][j][k + 1] = true;
                q.push([i, j, k + 1]);
            } else {
                for (let c = 0; c < 4; ++c) {
                    const [x, y] = [i + dirs[c], j + dirs[c + 1]];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y][k]) {
                        vis[x][y][k] = true;
                        q.push([x, y, k]);
                    }
                }
            }
        }
    }
    return -1;
}
