/**
 * @param {number} length
 * @param {number[][]} updates
 * @return {number[]}
 */
var getModifiedArray = function (length, updates) {
    let delta = new Array(length).fill(0);
    for (let [start, end, inc] of updates) {
        delta[start] += inc;
        if (end + 1 < length) {
            delta[end + 1] -= inc;
        }
    }
    for (let i = 1; i < length; ++i) {
        delta[i] += delta[i - 1];
    }
    return delta;
};
