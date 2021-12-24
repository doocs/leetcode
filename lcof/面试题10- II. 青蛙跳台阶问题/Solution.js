/**
 * @param {number} n
 * @return {number}
 */
var numWays = function (n) {
    let a = 0,
        b = 1;
    for (let i = 0; i < n; ++i) {
        const c = (a + b) % (1e9 + 7);
        a = b;
        b = c;
    }
    return b;
};
