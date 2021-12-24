/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) return -1;
    let left = 1,
        right = 10 ** 7;
    while (left < right) {
        let mid = (left + right) >> 1;
        if (arriveOnTime(dist, mid, hour)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
};

function arriveOnTime(dist, speed, hour) {
    let res = 0.0;
    let n = dist.length;
    for (let i = 0; i < n; i++) {
        let cost = parseFloat(dist[i]) / speed;
        if (i != n - 1) {
            cost = Math.ceil(cost);
        }
        res += cost;
    }
    return res <= hour;
}
