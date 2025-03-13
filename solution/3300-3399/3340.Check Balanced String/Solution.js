/**
 * @param {string} num
 * @return {boolean}
 */
var isBalanced = function (num) {
    const f = [0, 0];
    for (let i = 0; i < num.length; ++i) {
        f[i & 1] += +num[i];
    }
    return f[0] === f[1];
};
