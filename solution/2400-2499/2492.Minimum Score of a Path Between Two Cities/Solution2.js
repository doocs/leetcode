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
    const q = [1];
    vis[1] = true;

    while (q.length > 0) {
        for (let k = q.length; k > 0; --k) {
            const a = q.shift();
            for (const [b, w] of g[a]) {
                ans = Math.min(ans, w);
                if (!vis[b]) {
                    vis[b] = true;
                    q.push(b);
                }
            }
        }
    }
    return ans;
};
