/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantConnection = function (edges) {
    const n = edges.length;
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (let i = 0; ; ++i) {
        const pa = find(edges[i][0] - 1);
        const pb = find(edges[i][1] - 1);
        if (pa === pb) {
            return edges[i];
        }
        p[pa] = pb;
    }
};
