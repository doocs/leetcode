/**
 * @param {number} numBottles
 * @param {number} numExchange
 * @return {number}
 */
var numWaterBottles = function (numBottles, numExchange) {
    let sum = numBottles;
    while (numBottles >= numExchange) {
        numBottles = numBottles - numExchange + 1;
        sum++;
    }
    return sum;
};