var minScore = function (n, roads) {
    // 构建点到点的映射表
    const graph = Array.from({ length: n + 1 }, () => new Map());
    for (let [u, v, w] of roads) {
        graph[u].set(v, w);
        graph[v].set(u, w);
    }

    // DFS
    const vis = new Array(n).fill(false);
    let ans = Infinity;
    var dfs = function (u) {
        vis[u] = true;
        for (const [v, w] of graph[u]) {
            ans = Math.min(ans, w);
            if (!vis[v]) dfs(v);
        }
    };
    dfs(1);

    return ans;
};
