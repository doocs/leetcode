/**
 * @param {number[][]} bookings
 * @param {number} n
 * @return {number[]}
 */
var corpFlightBookings = function (bookings, n) {
    let delta = new Array(n).fill(0);
    for (let [start, end, num] of bookings) {
        delta[start - 1] += num;
        if (end != n) {
            delta[end] -= num;
        }
    }
    for (let i = 1; i < n; ++i) {
        delta[i] += delta[i - 1];
    }
    return delta;
};
