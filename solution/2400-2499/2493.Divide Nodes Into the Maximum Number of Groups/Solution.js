var magnificentSets = function (n, edges) {
    const graph = Array.from({ length: n + 1 }, () => new Set());
    for (const [u, v] of edges) {
        graph[u].add(v);
        graph[v].add(u);
    }
    const hash = new Map();

    // 2. BFS
    for (let i = 1; i <= n; i++) {
        let queue = [i];
        const dis = Array(n + 1).fill(0);
        dis[i] = 1;
        let mx = 1,
            mn = n;
        while (queue.length) {
            let next = [];
            for (let u of queue) {
                mn = Math.min(mn, u);
                for (const v of graph[u]) {
                    if (!dis[v]) {
                        dis[v] = dis[u] + 1;
                        mx = Math.max(mx, dis[v]);
                        next.push(v);
                    }
                    if (Math.abs(dis[u] - dis[v]) != 1) {
                        return -1;
                    }
                }
            }
            queue = next;
        }
        hash.set(mn, Math.max(mx, hash.get(mn) || 0));
    }

    let ans = 0;
    for (const [u, v] of hash) {
        ans += v;
    }
    return ans;
};
