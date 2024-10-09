function checkValid(matrix: number[][]): boolean {
    const n = matrix.length;
    const vis: boolean[] = Array(n + 1).fill(false);
    for (const row of matrix) {
        vis.fill(false);
        for (const x of row) {
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
        }
    }
    for (let j = 0; j < n; ++j) {
        vis.fill(false);
        for (let i = 0; i < n; ++i) {
            if (vis[matrix[i][j]]) {
                return false;
            }
            vis[matrix[i][j]] = true;
        }
    }
    return true;
}
