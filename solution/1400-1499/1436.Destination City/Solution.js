/**
 * @param {string[][]} paths
 * @return {string}
 */
var destCity = function (paths) {
    const s = new Set();
    for (const [a, _] of paths) {
        s.add(a);
    }
    for (const [_, b] of paths) {
        if (!s.has(b)) {
            return b;
        }
    }
    return '';
};
