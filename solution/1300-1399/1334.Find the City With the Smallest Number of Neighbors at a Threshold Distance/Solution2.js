function findTheCity(n, edges, distanceThreshold) {
    const g = Array.from({ length: n }, () => Array(n).fill(Infinity));
    for (const [f, t, w] of edges) {
        g[f][t] = g[t][f] = w;
    }
    for (let k = 0; k < n; ++k) {
        g[k][k] = 0;
        for (let i = 0; i < n; ++i) {
            for (let j = 0; j < n; ++j) {
                g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
            }
        }
    }

    let ans = n,
        cnt = n + 1;
    for (let i = n - 1; i >= 0; --i) {
        const t = g[i].filter(x => x <= distanceThreshold).length;
        if (t < cnt) {
            cnt = t;
            ans = i;
        }
    }
    return ans;
}
