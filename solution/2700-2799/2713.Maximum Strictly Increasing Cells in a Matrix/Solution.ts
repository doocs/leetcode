function maxIncreasingCells(mat: number[][]): number {
    const m = mat.length;
    const n = mat[0].length;
    const g: { [key: number]: [number, number][] } = {};

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!g[mat[i][j]]) {
                g[mat[i][j]] = [];
            }
            g[mat[i][j]].push([i, j]);
        }
    }

    const rowMax = Array(m).fill(0);
    const colMax = Array(n).fill(0);
    let ans = 0;

    const sortedKeys = Object.keys(g)
        .map(Number)
        .sort((a, b) => a - b);

    for (const key of sortedKeys) {
        const pos = g[key];
        const mx: number[] = [];

        for (const [i, j] of pos) {
            mx.push(1 + Math.max(rowMax[i], colMax[j]));
            ans = Math.max(ans, mx[mx.length - 1]);
        }

        for (let k = 0; k < pos.length; k++) {
            const [i, j] = pos[k];
            rowMax[i] = Math.max(rowMax[i], mx[k]);
            colMax[j] = Math.max(colMax[j], mx[k]);
        }
    }

    return ans;
}
