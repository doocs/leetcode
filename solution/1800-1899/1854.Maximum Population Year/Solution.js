/**
 * @param {number[][]} logs
 * @return {number}
 */
 var maximumPopulation = function(logs) {
    const offset = 1950;
    const len = 2050 - 1950 + 1;
    let delta = new Array(len).fill(0);
    for (let log of logs) {
        delta[log[0] - offset] += 1;
        delta[log[1] - offset] -= 1;
    }
    let max = 0;
    let total = 0;
    let index = 0;
    for (let i = 0; i < len; i++) {
        total += delta[i];
        if (total > max) {
            max = total;
            index = i;
        }
    }
    return index + offset;
};