/**
 * @param {number} n
 * @param {number} m
 * @return {number}
 */
var flowerGame = function (n, m) {
    const [a1, b1] = [(n + 1) >> 1, (m + 1) >> 1];
    const [a2, b2] = [n >> 1, m >> 1];
    return a1 * b2 + a2 * b1;
};
