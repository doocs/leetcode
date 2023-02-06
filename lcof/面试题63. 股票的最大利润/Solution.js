/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let mi = 1 << 30;
    let ans = 0;
    for (const x of prices) {
        ans = Math.max(ans, x - mi);
        mi = Math.min(mi, x);
    }
    return ans;
};
