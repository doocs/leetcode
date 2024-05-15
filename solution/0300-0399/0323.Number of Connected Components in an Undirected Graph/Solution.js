/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var countComponents = function (n, edges) {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis = Array(n).fill(false);
    const dfs = i => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        for (const j of g[i]) {
            dfs(j);
        }
        return 1;
    };
    return g.reduce((acc, _, i) => acc + dfs(i), 0);
};
