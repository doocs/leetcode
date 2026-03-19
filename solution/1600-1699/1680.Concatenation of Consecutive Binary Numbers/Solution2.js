/**
 * @param {number} n
 * @return {number}
 */
var concatenatedBinary = function (n) {
    const mod = 1_000_000_007;
    let ans = 0;
    let shift = 0;
    for (let i = 1; i <= n; i++) {
        if ((i & (i - 1)) === 0) {
            shift++;
        }
        ans = (((ans * (1 << shift)) % mod) + i) % mod;
    }
    return ans;
};
