/**
 * @param {number} n
 * @return {number}
 */
var countVowelPermutation = function (n) {
    const mod = 1e9 + 7;
    const f = Array(5).fill(1);
    for (let i = 1; i < n; ++i) {
        const g = Array(5).fill(0);
        g[0] = (f[1] + f[2] + f[4]) % mod;
        g[1] = (f[0] + f[2]) % mod;
        g[2] = (f[1] + f[3]) % mod;
        g[3] = f[2];
        g[4] = (f[2] + f[3]) % mod;
        f.splice(0, 5, ...g);
    }
    return f.reduce((a, b) => (a + b) % mod);
};
