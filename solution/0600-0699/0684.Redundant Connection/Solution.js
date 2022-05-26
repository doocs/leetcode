/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantConnection = function (edges) {
    let p = Array.from({ length: 1010 }, (_, i) => i);
    function find(x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
    for (let [a, b] of edges) {
        if (find(a) == find(b)) {
            return [a, b];
        }
        p[find(a)] = find(b);
    }
    return [];
};
