/**
 * @param {number} lowLimit
 * @param {number} highLimit
 * @return {number}
 */
var countBalls = function (lowLimit, highLimit) {
    const cnt = Array(50).fill(0);
    for (let i = lowLimit; i <= highLimit; ++i) {
        let y = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            y += x % 10;
        }
        ++cnt[y];
    }
    return Math.max(...cnt);
};
