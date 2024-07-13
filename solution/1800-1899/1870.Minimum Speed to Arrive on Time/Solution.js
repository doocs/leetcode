/**
 * @param {number[]} dist
 * @param {number} hour
 * @return {number}
 */
var minSpeedOnTime = function (dist, hour) {
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    const n = dist.length;
    const m = 10 ** 7;
    const check = v => {
        let s = 0;
        for (let i = 0; i < n; ++i) {
            const t = dist[i] / v;
            s += i === n - 1 ? t : Math.ceil(t);
        }
        return s <= hour;
    };
    let [l, r] = [1, m + 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
};
