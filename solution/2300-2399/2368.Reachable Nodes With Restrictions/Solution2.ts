function reachableNodes(n: number, edges: number[][], restricted: number[]): number {
    const g = Array.from({ length: n }, () => []);
    const vis = new Array(n).fill(false);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const v of restricted) {
        vis[v] = true;
    }
    const q = [0];
    let ans = 0;
    while (q.length) {
        const i = q.shift();
        ++ans;
        vis[i] = true;
        for (const j of g[i]) {
            if (!vis[j]) {
                q.push(j);
            }
        }
    }
    return ans;
}
