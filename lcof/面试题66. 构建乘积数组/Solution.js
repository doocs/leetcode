/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function (a) {
    const n = a.length;
    let output = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        output[i] = left;
        left *= a[i];
    }
    for (let i = n - 1, right = 1; i >= 0; --i) {
        output[i] *= right;
        right *= a[i];
    }
    return output;
};
