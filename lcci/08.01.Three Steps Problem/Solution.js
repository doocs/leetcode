/**
 * @param {number} n
 * @return {number}
 */
var waysToStep = function (n) {
    if (n < 3) return n;
    let a = 1,
        b = 2,
        c = 4;
    for (let i = 3; i < n; i++) {
        [a, b, c] = [b, c, (a + b + c) % 1000000007];
    }
    return c;
};
