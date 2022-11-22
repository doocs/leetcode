/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let ans = 0;
    for (let i = 1; i < prices.length; i++) {
        ans += Math.max(0, prices[i] - prices[i - 1]);
    }
    return ans;
};
