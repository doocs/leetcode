function updateMatrix(mat: number[][]): number[][] {
    const [m, n] = [mat.length, mat[0].length];
    const ans: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => -1));
    const q: [number, number][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (mat[i][j] === 0) {
                q.push([i, j]);
                ans[i][j] = 0;
            }
        }
    }
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.length) {
        const [i, j] = q.shift()!;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] === -1) {
                ans[x][y] = ans[i][j] + 1;
                q.push([x, y]);
            }
        }
    }
    return ans;
}
