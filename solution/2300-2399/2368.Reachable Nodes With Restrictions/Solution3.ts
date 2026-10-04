function reachableNodes(n: number, edges: number[][], restricted: number[]): number {
    const vis: boolean[] = Array(n).fill(false);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const i of restricted) {
        vis[i] = true;
    }
    let ans = 0;
    const stk: number[] = [0];
    while (stk.length) {
        const i = stk.pop()!;
        if (vis[i]) {
            continue;
        }
        vis[i] = true;
        ++ans;
        for (const j of g[i]) {
            if (!vis[j]) {
                stk.push(j);
            }
        }
    }
    return ans;
}
