/**
 * @param {string[][]} paths
 * @return {string}
 */
var destCity = function (paths) {
    const s = new Set(paths.map(([a, _]) => a));
    return paths.find(([_, b]) => !s.has(b))[1];
};
