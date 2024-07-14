/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    if (edges.length !== n - 1) {
        return false;
    }
    const g = Array.from({ length: n }, () => []);
    const vis = Array.from({ length: n }, () => false);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const dfs = i => {
        vis[i] = true;
        --n;
        for (const j of g[i]) {
            if (!vis[j]) {
                dfs(j);
            }
        }
    };
    dfs(0);
    return n === 0;
};
