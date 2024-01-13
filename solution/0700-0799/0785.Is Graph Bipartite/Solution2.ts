function isBipartite(graph: number[][]): boolean {
    const n = graph.length;
    let p = new Array(n);
    for (let i = 0; i < n; ++i) {
        p[i] = i;
    }
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let u = 0; u < n; ++u) {
        for (let v of graph[u]) {
            if (find(u) == find(v)) {
                return false;
            }
            p[find(v)] = find(graph[u][0]);
        }
    }
    return true;
}
