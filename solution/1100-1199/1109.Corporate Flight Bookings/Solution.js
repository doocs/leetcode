/**
 * @param {number[][]} bookings
 * @param {number} n
 * @return {number[]}
 */
 var corpFlightBookings = function(bookings, n) {
    let delta = new Array(n).fill(0);
    for (let book of bookings) {
        let [start, end, num] = book;
        start -= 1;
        delta[start] += num;
        if (end != n) {
            delta[end] -= num;
        }
    }
    for (let i = 1; i < n; i++) {
        delta[i] += delta[i - 1];
    }
    return delta;
};