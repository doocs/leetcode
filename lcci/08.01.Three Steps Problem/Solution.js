/**
 * @param {number} n
 * @return {number}
 */
var waysToStep = function (n) {
    let [a, b, c] = [1, 2, 4];
    const mod = 1e9 + 7;
    for (let i = 1; i < n; ++i) {
        [a, b, c] = [b, c, (a + b + c) % mod];
    }
    return a;
};
