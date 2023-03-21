/**
 * @param {number} n
 * @return {number[]}
 */
var grayCode = function (n) {
    const ans = [];
    for (let i = 0; i < 1 << n; ++i) {
        ans.push(i ^ (i >> 1));
    }
    return ans;
};
