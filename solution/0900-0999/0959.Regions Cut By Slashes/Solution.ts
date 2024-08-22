function regionsBySlashes(grid: string[]): number {
    const find = (x: number) => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };

    const union = (a: number, b: number) => {
        const pa = find(a);
        const pb = find(b);
        if (pa !== pb) {
            p[pa] = pb;
            size--;
        }
    };

    const n = grid.length;
    let size = n * n * 4;
    const p = Array.from({ length: size }, (_, i) => i);

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const k = i * n + j;
            if (i < n - 1) {
                union(4 * k + 2, (k + n) * 4);
            }
            if (j < n - 1) {
                union(4 * k + 1, (k + 1) * 4 + 3);
            }
            if (grid[i][j] === '/') {
                union(4 * k, 4 * k + 3);
                union(4 * k + 1, 4 * k + 2);
            } else if (grid[i][j] === '\\') {
                union(4 * k, 4 * k + 1);
                union(4 * k + 2, 4 * k + 3);
            } else {
                union(4 * k, 4 * k + 1);
                union(4 * k + 1, 4 * k + 2);
                union(4 * k + 2, 4 * k + 3);
            }
        }
    }

    return size;
}
