/**
 * @param {number} n
 * @return {number}
 */
var fib = function (n) {
    let a = 0;
    let b = 1;
    while (n--) {
        const c = a + b;
        a = b;
        b = c;
    }
    return a;
};
