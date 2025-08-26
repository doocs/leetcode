/**
 * @param {number} n
 * @param {number} m
 * @return {number}
 */
var flowerGame = function (n, m) {
    return Number(((BigInt(n) * BigInt(m)) / 2n) | 0n);
};
