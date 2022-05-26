/**
 * @param {number} n
 * @return {number}
 */
var cuttingRope = function (n) {
    if (n <= 3) return n - 1;
    let a = ~~(n / 3);
    let b = n % 3;
    const MOD = 1e9 + 7;
    function myPow(x) {
        let r = 1;
        for (let i = 0; i < x; i++) {
            r = (r * 3) % MOD;
        }
        return r;
    }
    if (b === 1) {
        return (myPow(a - 1) * 4) % MOD;
    }
    if (b === 0) return myPow(a) % MOD;
    return (myPow(a) * 2) % MOD;
};
