/**
 * @param {number[][]} edges
 * @return {number[]}
 */
var findRedundantDirectedConnection = function (edges) {
    const n = edges.length;
    const ind = Array(n).fill(0);
    for (const [_, v] of edges) {
        ++ind[v - 1];
    }
    const dup = [];
    for (let i = 0; i < n; ++i) {
        if (ind[edges[i][1] - 1] === 2) {
            dup.push(i);
        }
    }
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    if (dup.length) {
        for (let i = 0; i < n; ++i) {
            if (i === dup[1]) {
                continue;
            }
            const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
            if (pu === pv) {
                return edges[dup[0]];
            }
            p[pu] = pv;
        }
        return edges[dup[1]];
    }
    for (let i = 0; ; ++i) {
        const [pu, pv] = [find(edges[i][0] - 1), find(edges[i][1] - 1)];
        if (pu === pv) {
            return edges[i];
        }
        p[pu] = pv;
    }
};
