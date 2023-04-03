function collectTheCoins(coins: number[], edges: number[][]): number {
    const n = coins.length;
    const g: Set<number>[] = new Array(n).fill(0).map(() => new Set<number>());
    for (const [a, b] of edges) {
        g[a].add(b);
        g[b].add(a);
    }
    let q: number[] = [];
    for (let i = 0; i < n; ++i) {
        if (coins[i] === 0 && g[i].size === 1) {
            q.push(i);
        }
    }
    while (q.length) {
        const i = q.pop()!;
        for (const j of g[i]) {
            g[j].delete(i);
            if (coins[j] === 0 && g[j].size === 1) {
                q.push(j);
            }
        }
        g[i].clear();
    }
    q = [];
    for (let k = 0; k < 2; ++k) {
        for (let i = 0; i < n; ++i) {
            if (g[i].size === 1) {
                q.push(i);
            }
        }
        for (const i of q) {
            for (const j of g[i]) {
                g[j].delete(i);
            }
            g[i].clear();
        }
    }
    let ans = 0;
    for (const [a, b] of edges) {
        if (g[a].size > 0 && g[b].size > 0) {
            ans += 2;
        }
    }
    return ans;
}
