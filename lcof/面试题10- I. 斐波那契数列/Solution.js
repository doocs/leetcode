/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    let a = 0;
    let b = 1;
    while (n--) {
        [a, b] = [b, (a + b) % (1e9 + 7)];
    }
    return a;
};
