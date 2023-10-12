/**
 * @param {number} numBottles
 * @param {number} numExchange
 * @return {number}
 */
var numWaterBottles = function (numBottles, numExchange) {
    let ans = numBottles;
    for (; numBottles >= numExchange; ++ans) {
        numBottles -= numExchange - 1;
    }
    return ans;
};
