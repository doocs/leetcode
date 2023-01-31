/**
 * @param {number} n
 * @return {number}
 */
var numWays = function (n) {
    let a = (b = 1);
    while (n--) {
        [a, b] = [b, (a + b) % (1e9 + 7)];
    }
    return a;
};
