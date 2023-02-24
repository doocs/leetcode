function longestCycle(edges: number[]): number {
    const n = edges.length;
    const vis = new Array(n).fill(false);
    let ans = -1;
    for (let i = 0; i < n; ++i) {
        if (vis[i]) {
            continue;
        }
        let j = i;
        const cycle: number[] = [];
        for (; j != -1 && !vis[j]; j = edges[j]) {
            vis[j] = true;
            cycle.push(j);
        }
        if (j == -1) {
            continue;
        }
        for (let k = 0; k < cycle.length; ++k) {
            if (cycle[k] == j) {
                ans = Math.max(ans, cycle.length - k);
                break;
            }
        }
    }
    return ans;
}
