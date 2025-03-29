function isEscapePossible(blocked: number[][], source: number[], target: number[]): boolean {
    const n = 10 ** 6;
    const m = (blocked.length ** 2) >> 1;
    const dirs = [-1, 0, 1, 0, -1];

    const s = new Set<number>();
    const f = (i: number, j: number): number => i * n + j;

    for (const [x, y] of blocked) {
        s.add(f(x, y));
    }

    const dfs = (sx: number, sy: number, tx: number, ty: number, vis: Set<number>): boolean => {
        vis.add(f(sx, sy));
        if (vis.size > m) {
            return true;
        }
        for (let k = 0; k < 4; k++) {
            const x = sx + dirs[k],
                y = sy + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                if (x === tx && y === ty) {
                    return true;
                }
                const key = f(x, y);
                if (!s.has(key) && !vis.has(key) && dfs(x, y, tx, ty, vis)) {
                    return true;
                }
            }
        }
        return false;
    };

    return (
        dfs(source[0], source[1], target[0], target[1], new Set()) &&
        dfs(target[0], target[1], source[0], source[1], new Set())
    );
}
