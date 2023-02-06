/**
 * @param {number[]} a
 * @return {number[]}
 */
var constructArr = function (a) {
    const n = a.length;
    const ans = new Array(n);
    for (let i = 0, left = 1; i < n; ++i) {
        ans[i] = left;
        left *= a[i];
    }
    for (let i = n - 1, right = 1; ~i; --i) {
        ans[i] *= right;
        right *= a[i];
    }
    return ans;
};
