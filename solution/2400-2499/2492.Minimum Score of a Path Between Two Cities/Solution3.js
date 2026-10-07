/**
 * @param {number} n
 * @param {number[][]} roads
 * @return {number}
 */
var minScore = function (n, roads) {
    const g = Array.from({ length: n + 1 }, () => []);
    for (const [a, b, w] of roads) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }
    const vis = new Array(n + 1).fill(false);
    let ans = Infinity;
    const stk = [1];
    while (stk.length) {
        const a = stk.pop();
        if (vis[a]) {
            continue;
        }
        vis[a] = true;
        for (const [b, w] of g[a]) {
            ans = Math.min(ans, w);
            if (!vis[b]) {
                stk.push(b);
            }
        }
    }
    return ans;
};
