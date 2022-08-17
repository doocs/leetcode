/**
 * @param {number[][]} costs
 * @return {number}
 */
var minCost = function (costs) {
    let [a, b, c] = [0, 0, 0];
    for (let [ca, cb, cc] of costs) {
        [a, b, c] = [
            Math.min(b, c) + ca,
            Math.min(a, c) + cb,
            Math.min(a, b) + cc,
        ];
    }
    return Math.min(a, b, c);
};
