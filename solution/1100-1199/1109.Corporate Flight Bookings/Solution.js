/**
 * @param {number[][]} bookings
 * @param {number} n
 * @return {number[]}
 */
var corpFlightBookings = function (bookings, n) {
    const ans = new Array(n).fill(0);
    for (const [first, last, seats] of bookings) {
        ans[first - 1] += seats;
        if (last < n) {
            ans[last] -= seats;
        }
    }
    for (let i = 1; i < n; ++i) {
        ans[i] += ans[i - 1];
    }
    return ans;
};
