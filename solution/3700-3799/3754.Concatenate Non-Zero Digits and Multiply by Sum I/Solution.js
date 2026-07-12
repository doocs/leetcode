/**
 * @param {number} n
 * @return {number}
 */
var sumAndMultiply = function (n) {
    let p = 1;
    let x = 0;
    let s = 0;

    while (n > 0) {
        const v = n % 10;
        if (v !== 0) {
            s += v;
            x += p * v;
            p *= 10;
        }
        n = Math.floor(n / 10);
    }

    return x * s;
};
