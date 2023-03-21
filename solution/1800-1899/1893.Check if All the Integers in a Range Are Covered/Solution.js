/**
 * @param {number[][]} ranges
 * @param {number} left
 * @param {number} right
 * @return {boolean}
 */
var isCovered = function (ranges, left, right) {
    const diff = new Array(52).fill(0);
    for (const [l, r] of ranges) {
        ++diff[l];
        --diff[r + 1];
    }
    let cur = 0;
    for (let i = 0; i < 52; ++i) {
        cur += diff[i];
        if (i >= left && i <= right && cur <= 0) {
            return false;
        }
    }
    return true;
};
