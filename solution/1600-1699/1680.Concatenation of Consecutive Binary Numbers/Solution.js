/**
 * @param {number} n
 * @return {number}
 */
var concatenatedBinary = function (n) {
    const mod = 1_000_000_007;
    let ans = 0;
    for (let i = 1; i <= n; i++) {
        ans = (((ans * (1 << (32 - Math.clz32(i)))) % mod) + i) % mod;
    }
    return ans;
};
