function maxPoints(points: number[][]): number {
    const n = points[0].length;
    const f: number[] = new Array(n).fill(0);
    for (const p of points) {
        const g: number[] = new Array(n).fill(0);
        let lmx = -Infinity;
        let rmx = -Infinity;
        for (let j = 0; j < n; ++j) {
            lmx = Math.max(lmx, f[j] + j);
            g[j] = Math.max(g[j], p[j] + lmx - j);
        }
        for (let j = n - 1; ~j; --j) {
            rmx = Math.max(rmx, f[j] - j);
            g[j] = Math.max(g[j], p[j] + rmx + j);
        }
        f.splice(0, n, ...g);
    }
    return Math.max(...f);
}
