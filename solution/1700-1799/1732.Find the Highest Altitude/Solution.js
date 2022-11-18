/**
 * @param {number[]} gain
 * @return {number}
 */
var largestAltitude = function (gain) {
    let ans = 0;
    let s = 0;
    for (const v of gain) {
        s += v;
        ans = Math.max(ans, s);
    }
    return ans;
};
