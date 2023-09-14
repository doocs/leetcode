/**
 * @param {number} primeFactors
 * @return {number}
 */
var maxNiceDivisors = function (primeFactors) {
    if (primeFactors < 4) {
        return primeFactors;
    }
    const mod = 1e9 + 7;
    const qpow = (a, n) => {
        let ans = 1;
        for (; n; n >>= 1) {
            if (n & 1) {
                ans = Number((BigInt(ans) * BigInt(a)) % BigInt(mod));
            }
            a = Number((BigInt(a) * BigInt(a)) % BigInt(mod));
        }
        return ans;
    };
    const k = Math.floor(primeFactors / 3);
    if (primeFactors % 3 === 0) {
        return qpow(3, k);
    }
    if (primeFactors % 3 === 1) {
        return (4 * qpow(3, k - 1)) % mod;
    }
    return (2 * qpow(3, k)) % mod;
};
