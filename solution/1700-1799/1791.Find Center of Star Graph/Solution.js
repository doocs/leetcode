/**
 * @param {number[][]} edges
 * @return {number}
 */
var findCenter = function (edges) {
    const [a, b] = edges[0];
    const [c, d] = edges[1];
    return a == c || a == d ? a : b;
};
