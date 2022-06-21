/**
 * @param {number} n
 * @return {number}
 */
var countPrimes = function (n) {
    let primes = new Array(n).fill(true);
    let ans = 0;
    for (let i = 2; i < n; ++i) {
        if (primes[i]) {
            ++ans;
            for (let j = i + i; j < n; j += i) {
                primes[j] = false;
            }
        }
    }
    return ans;
};
