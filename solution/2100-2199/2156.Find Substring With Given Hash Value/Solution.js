/**
 * @param {string} s
 * @param {number} power
 * @param {number} modulo
 * @param {number} k
 * @param {number} hashValue
 * @return {string}
 */
var subStrHash = function (s, power, modulo, k, hashValue) {
    power = BigInt(power);
    modulo = BigInt(modulo);
    hashValue = BigInt(hashValue);
    const n = s.length;
    let pk = 1n;
    let ac = 0n;
    // 倒序滑动窗口
    for (let i = n - 1; i > n - 1 - k; i--) {
        ac = (ac * power + getCode(s, i)) % modulo;
        pk = (pk * power) % modulo;
    }
    let ans = -1;
    if (ac == hashValue) {
        ans = n - k;
    }
    for (let i = n - 1 - k; i >= 0; i--) {
        let pre = (getCode(s, i + k) * pk) % modulo;
        ac = (ac * power + getCode(s, i) - pre + modulo) % modulo;
        if (ac == hashValue) {
            ans = i;
        }
    }
    return ans == -1 ? '' : s.substring(ans, ans + k);
};

function getCode(str, index) {
    return BigInt(str.charCodeAt(index) - 'a'.charCodeAt(0) + 1);
}
