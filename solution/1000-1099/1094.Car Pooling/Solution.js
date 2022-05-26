/**
 * @param {number[][]} trips
 * @param {number} capacity
 * @return {boolean}
 */
var carPooling = function (trips, capacity) {
    let delta = new Array(1001).fill(0);
    for (let [num, start, end] of trips) {
        delta[start] += num;
        delta[end] -= num;
    }
    let s = 0;
    for (let num of delta) {
        s += num;
        if (s > capacity) {
            return false;
        }
    }
    return true;
};
