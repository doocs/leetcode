/**
 * @param {number} n
 * @param {number[][]} edges
 * @return {boolean}
 */
var validTree = function (n, edges) {
    const p = Array.from({ length: n }, (_, i) => i);
    const find = x => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    for (const [a, b] of edges) {
        const pa = find(a);
        const pb = find(b);
        if (pa === pb) {
            return false;
        }
        p[pa] = pb;
        --n;
    }
    return n === 1;
};
