function countVisitedNodes(edges: number[]): number[] {
    const n = edges.length;
    const ans: number[] = Array(n).fill(0);
    const vis: number[] = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        if (ans[i] === 0) {
            let [cnt, j] = [0, i];
            while (vis[j] === 0) {
                vis[j] = ++cnt;
                j = edges[j];
            }
            let [cycle, total] = [0, cnt + ans[j]];
            if (ans[j] === 0) {
                cycle = cnt - vis[j] + 1;
            }
            j = i;
            while (ans[j] === 0) {
                ans[j] = Math.max(total--, cycle);
                j = edges[j];
            }
        }
    }
    return ans;
}
