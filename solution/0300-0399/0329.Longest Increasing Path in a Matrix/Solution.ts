function longestIncreasingPath(matrix: number[][]): number {
    const m = matrix.length;
    const n = matrix[0].length;
    const outdegree: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    const length: number[][] = Array.from({ length: m }, () => Array(n).fill(1));
    const dirs = [-1, 0, 1, 0, -1];
    const q: [number, number][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                    ++outdegree[i][j];
                }
            }
            if (outdegree[i][j] === 0) q.push([i, j]);
        }
    }
    let ans = 1;
    for (let head = 0; head < q.length; ++head) {
        const [i, j] = q[head];
        ans = Math.max(ans, length[i][j]);
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                length[x][y] = Math.max(length[x][y], length[i][j] + 1);
                if (--outdegree[x][y] === 0) q.push([x, y]);
            }
        }
    }
    return ans;
}
