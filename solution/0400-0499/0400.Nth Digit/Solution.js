/**
 * @param {number} n
 * @return {number}
 */
var findNthDigit = function (n) {
    let k = 1,
        cnt = 9;
    while (k * cnt < n) {
        n -= k * cnt;
        ++k;
        cnt *= 10;
    }
    const num = Math.pow(10, k - 1) + (n - 1) / k;
    const idx = (n - 1) % k;
    return num.toString()[idx];
};
