/**
 * @param {string} s
 * @param {number} power
 * @param {number} modulo
 * @param {number} k
 * @param {number} hashValue
 * @return {string}
 */
var subStrHash = function (s, power, modulo, k, hashValue) {
    let h = BigInt(0),
        p = BigInt(1);
    const n = s.length;
    const mod = BigInt(modulo);
    for (let i = n - 1; i >= n - k; --i) {
        const val = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = (((h * BigInt(power)) % mod) + val) % mod;
        if (i !== n - k) {
            p = (p * BigInt(power)) % mod;
        }
    }
    let j = n - k;
    for (let i = n - k - 1; i >= 0; --i) {
        const pre = BigInt(s.charCodeAt(i + k) - 'a'.charCodeAt(0) + 1);
        const cur = BigInt(s.charCodeAt(i) - 'a'.charCodeAt(0) + 1);
        h = ((((h - ((pre * p) % mod) + mod) * BigInt(power)) % mod) + cur) % mod;
        if (Number(h) === hashValue) {
            j = i;
        }
    }
    return s.substring(j, j + k);
};
