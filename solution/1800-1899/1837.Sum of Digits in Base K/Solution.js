/**
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var sumBase = function (n, k) {
    let ans = 0;
    while (n) {
        ans += n % k;
        n = Math.floor(n / k);
    }
    return ans;
};
