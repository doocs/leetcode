/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {number}
 */
var countComponents = function (n, edges) {
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
    for (const [a, b] of edges) {
        p[find(a)] = find(b);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (i == find(i)) {
            ++ans;
        }
    }
    return ans;
};
