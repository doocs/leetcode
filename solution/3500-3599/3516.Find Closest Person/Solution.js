/**
 * @param {number} x
 * @param {number} y
 * @param {number} z
 * @return {number}
 */
var findClosest = function (x, y, z) {
    const a = Math.abs(x - z);
    const b = Math.abs(y - z);
    return a === b ? 0 : a < b ? 1 : 2;
};
