/**
 * @param {number[]} position
 * @return {number}
 */
var minCostToMoveChips = function (position) {
    let a = 0;
    for (let v of position) {
        a += v % 2;
    }
    let b = position.length - a;
    return Math.min(a, b);
};
