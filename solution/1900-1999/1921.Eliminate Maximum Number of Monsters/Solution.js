/**
 * @param {number[]} dist
 * @param {number[]} speed
 * @return {number}
 */
var eliminateMaximum = function (dist, speed) {
    let arr = [];
    for (let i = 0; i < dist.length; i++) {
        arr[i] = dist[i] / speed[i];
    }
    arr.sort((a, b) => a - b);
    let ans = 0;
    while (arr[0] > ans) {
        arr.shift();
        ++ans;
    }
    return ans;
};
