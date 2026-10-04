function countComponents(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis: boolean[] = Array(n).fill(false);
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (vis[i]) {
            continue;
        }
        ++ans;
        const stk: number[] = [i];
        vis[i] = true;
        while (stk.length) {
            const u = stk.pop()!;
            for (const v of g[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    stk.push(v);
                }
            }
        }
    }
    return ans;
}
