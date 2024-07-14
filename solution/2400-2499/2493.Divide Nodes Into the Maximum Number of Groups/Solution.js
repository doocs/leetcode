/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var magnificentSets = function (n, edges) {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a - 1].push(b - 1);
        g[b - 1].push(a - 1);
    }
    const d = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        const q = [i];
        const dist = Array(n).fill(0);
        dist[i] = 1;
        let mx = 1;
        let root = i;
        while (q.length) {
            const a = q.shift();
            root = Math.min(root, a);
            for (const b of g[a]) {
                if (dist[b] === 0) {
                    dist[b] = dist[a] + 1;
                    mx = Math.max(mx, dist[b]);
                    q.push(b);
                } else if (Math.abs(dist[b] - dist[a]) !== 1) {
                    return -1;
                }
            }
        }
        d[root] = Math.max(d[root], mx);
    }
    return d.reduce((a, b) => a + b);
};
