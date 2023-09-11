function minAreaFreeRect(points: number[][]): number {
    const n = points.length;
    const f = (x: number, y: number): number => x * 40001 + y;
    const s: Set<number> = new Set();
    for (const [x, y] of points) {
        s.add(f(x, y));
    }
    let ans = Number.MAX_VALUE;
    for (let i = 0; i < n; ++i) {
        const [x1, y1] = points[i];
        for (let j = 0; j < n; ++j) {
            if (j !== i) {
                const [x2, y2] = points[j];
                for (let k = j + 1; k < n; ++k) {
                    if (k !== i) {
                        const [x3, y3] = points[k];
                        const x4 = x2 - x1 + x3;
                        const y4 = y2 - y1 + y3;
                        if (s.has(f(x4, y4))) {
                            if ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1) === 0) {
                                const ww = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                                const hh = (x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1);
                                ans = Math.min(ans, Math.sqrt(ww * hh));
                            }
                        }
                    }
                }
            }
        }
    }
    return ans === Number.MAX_VALUE ? 0 : ans;
}
