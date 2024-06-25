function maximumDetonation(bombs: number[][]): number {
    const n = bombs.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; ++i) {
        const [x1, y1, r1] = bombs[i];
        for (let j = i + 1; j < n; ++j) {
            const [x2, y2, r2] = bombs[j];
            const d = Math.hypot(x1 - x2, y1 - y2);
            if (d <= r1) {
                g[i].push(j);
            }
            if (d <= r2) {
                g[j].push(i);
            }
        }
    }
    let ans = 0;
    for (let k = 0; k < n; ++k) {
        const vis: Set<number> = new Set([k]);
        const q: number[] = [k];
        for (const i of q) {
            for (const j of g[i]) {
                if (!vis.has(j)) {
                    vis.add(j);
                    q.push(j);
                }
            }
        }
        if (vis.size === n) {
            return n;
        }
        ans = Math.max(ans, vis.size);
    }
    return ans;
}
