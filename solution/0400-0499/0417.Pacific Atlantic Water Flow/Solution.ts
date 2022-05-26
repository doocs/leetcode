function pacificAtlantic(heights: number[][]): number[][] {
    const m = heights.length;
    const n = heights[0].length;
    const dirs = [
        [1, 0],
        [0, 1],
        [-1, 0],
        [0, -1],
    ];
    const gird = new Array(m).fill(0).map(() => new Array(n).fill(0));
    const isVis = new Array(m).fill(0).map(() => new Array(n).fill(false));

    const dfs = (i: number, j: number) => {
        if (isVis[i][j]) {
            return;
        }
        gird[i][j]++;
        isVis[i][j] = true;
        const h = heights[i][j];
        for (const [x, y] of dirs) {
            if (h <= (heights[i + x] ?? [])[j + y]) {
                dfs(i + x, j + y);
            }
        }
    };

    for (let i = 0; i < n; i++) {
        dfs(0, i);
    }
    for (let i = 0; i < m; i++) {
        dfs(i, 0);
    }
    isVis.forEach(v => v.fill(false));
    for (let i = 0; i < n; i++) {
        dfs(m - 1, i);
    }
    for (let i = 0; i < m; i++) {
        dfs(i, n - 1);
    }

    const res = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (gird[i][j] === 2) {
                res.push([i, j]);
            }
        }
    }
    return res;
}
