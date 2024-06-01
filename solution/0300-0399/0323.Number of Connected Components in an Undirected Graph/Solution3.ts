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
        vis[i] = true;
        ++ans;
        const q: number[] = [i];
        while (q.length) {
            const a = q.pop()!;
            for (const b of g[a]) {
                if (!vis[b]) {
                    vis[b] = true;
                    q.push(b);
                }
            }
        }
    }
    return ans;
}
