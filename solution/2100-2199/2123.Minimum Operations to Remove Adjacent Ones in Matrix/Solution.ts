function minimumOperations(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const match: number[] = Array(m * n).fill(-1);
    const vis: Set<number> = new Set();
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if ((i + j) % 2 && grid[i][j]) {
                const x = i * n + j;
                g.set(x, []);
                if (i < m - 1 && grid[i + 1][j]) {
                    g.get(x)!.push(x + n);
                }
                if (i && grid[i - 1][j]) {
                    g.get(x)!.push(x - n);
                }
                if (j < n - 1 && grid[i][j + 1]) {
                    g.get(x)!.push(x + 1);
                }
                if (j && grid[i][j - 1]) {
                    g.get(x)!.push(x - 1);
                }
            }
        }
    }
    const find = (i: number): number => {
        for (const j of g.get(i)!) {
            if (!vis.has(j)) {
                vis.add(j);
                if (match[j] === -1 || find(match[j])) {
                    match[j] = i;
                    return 1;
                }
            }
        }
        return 0;
    };
    let ans = 0;
    for (const i of g.keys()) {
        ans += find(i);
        vis.clear();
    }
    return ans;
}
