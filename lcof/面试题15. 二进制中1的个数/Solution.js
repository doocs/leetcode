/**
 * @param {number} n - a positive integer
 * @return {number}
 */
var hammingWeight = function (n) {
    let ans = 0;
    while (n != 0) {
        n &= n - 1;
        ++ans;
    }
    return ans;
};
