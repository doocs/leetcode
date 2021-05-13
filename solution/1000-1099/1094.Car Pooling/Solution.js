/**
 * @param {number[][]} trips
 * @param {number} capacity
 * @return {boolean}
 */
 var carPooling = function(trips, capacity) {
    let delta = new Array();
    for (let trip of trips) {
        let [num, start, end] = trip;
        delta[start] = (delta[start] || 0) + num;
        delta[end] = (delta[end] || 0) - num;
    }
    let total = 0;
    for (let i = 0; i < delta.length; i++) {
        let cur = delta[i];
        if (cur == undefined) continue;
        total += cur;
        if (total > capacity) return false;
    }
    return true;
};