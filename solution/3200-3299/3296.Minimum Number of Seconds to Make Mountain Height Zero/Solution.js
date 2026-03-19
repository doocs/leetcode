/**
 * @param {number} mountainHeight
 * @param {number[]} workerTimes
 * @return {number}
 */
var minNumberOfSeconds = function (mountainHeight, workerTimes) {
    const check = t => {
        let h = 0n;
        for (const wt of workerTimes) {
            h += BigInt(Math.floor(Math.sqrt((Number(t) * 2.0) / wt + 0.25) - 0.5));
        }
        return h >= BigInt(mountainHeight);
    };

    let l = 1n;
    let r = 10000000000000000n;

    while (l < r) {
        const mid = (l + r) >> 1n;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1n;
        }
    }

    return Number(l);
};
