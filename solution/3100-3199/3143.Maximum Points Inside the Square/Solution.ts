function maxPointsInsideSquare(points: number[][], s: string): number {
    const n = points.length;
    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < n; ++i) {
        const [x, y] = points[i];
        const key = Math.max(Math.abs(x), Math.abs(y));
        if (!g.has(key)) {
            g.set(key, []);
        }
        g.get(key)!.push(i);
    }
    const keys = Array.from(g.keys()).sort((a, b) => a - b);
    const vis: boolean[] = Array(26).fill(false);
    let ans = 0;
    for (const key of keys) {
        const idx = g.get(key)!;
        for (const i of idx) {
            const j = s.charCodeAt(i) - 'a'.charCodeAt(0);
            if (vis[j]) {
                return ans;
            }
            vis[j] = true;
        }
        ans += idx.length;
    }
    return ans;
}
