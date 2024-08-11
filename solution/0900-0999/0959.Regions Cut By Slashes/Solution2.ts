function regionsBySlashes(grid: string[]): number {
    const createGraph = () => {
        const n = grid.length;
        const g = Array.from({ length: n * 2 }, () => Array(n * 2).fill(0));

        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                const [y, x] = [i * 2, j * 2];

                switch (grid[i][j]) {
                    case '/':
                        g[y][x] = g[y + 1][x + 1] = 0;
                        g[y][x + 1] = g[y + 1][x] = 1;
                        break;

                    case '\\':
                        g[y][x] = g[y + 1][x + 1] = 2;
                        g[y][x + 1] = g[y + 1][x] = 0;
                        break;

                    default:
                        g[y][x] = g[y][x + 1] = g[y + 1][x] = g[y + 1][x + 1] = 0;
                        break;
                }
            }
        }

        return g;
    };

    const isValid = (x: number) => 0 <= x && x < n;
    const dfs = (i: number, j: number) => {
        if (!isValid(i) || !isValid(j) || g[i][j]) return;

        g[i][j] = -1;
        const dirs = [-1, 0, 1, 0, -1];
        const neighbours: number[] = [];

        for (let d = 0; d < 4; d++) {
            const [y, x] = [i + dirs[d], j + dirs[d + 1]];

            if (isValid(y) && isValid(x)) {
                dfs(y, x);
                neighbours.push(g[y][x]);
            } else {
                neighbours.push(-1);
            }
        }

        const [top, right, bottom, left] = neighbours;
        if (top === 1 && right === 1) dfs(i - 1, j + 1);
        if (bottom === 1 && left === 1) dfs(i + 1, j - 1);
        if (top === 2 && left === 2) dfs(i - 1, j - 1);
        if (bottom === 2 && right === 2) dfs(i + 1, j + 1);
    };

    const g = createGraph();
    const n = g.length;
    let res = 0;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (g[i][j] === 0) {
                dfs(i, j);
                res++;
            }
        }
    }

    return res;
}
