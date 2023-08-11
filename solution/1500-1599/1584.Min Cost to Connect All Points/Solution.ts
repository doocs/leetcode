function minCostConnectPoints(points: number[][]): number {
    const n = points.length;
    const g: number[][] = Array(n)
        .fill(0)
        .map(() => Array(n).fill(0));
    const dist: number[] = Array(n).fill(1 << 30);
    const vis: boolean[] = Array(n).fill(false);
    for (let i = 0; i < n; ++i) {
        const [x1, y1] = points[i];
        for (let j = i + 1; j < n; ++j) {
            const [x2, y2] = points[j];
            const t = Math.abs(x1 - x2) + Math.abs(y1 - y2);
            g[i][j] = t;
            g[j][i] = t;
        }
    }
    let ans = 0;
    dist[0] = 0;
    for (let i = 0; i < n; ++i) {
        let j = -1;
        for (let k = 0; k < n; ++k) {
            if (!vis[k] && (j === -1 || dist[k] < dist[j])) {
                j = k;
            }
        }
        vis[j] = true;
        ans += dist[j];
        for (let k = 0; k < n; ++k) {
            if (!vis[k]) {
                dist[k] = Math.min(dist[k], g[j][k]);
            }
        }
    }
    return ans;
}
