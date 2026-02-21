/**
 * @param {number} n
 * @return {number}
 */
var binaryGap = function (n) {
    let ans = 0;
    for (let pre = 100, cur = 0; n; n >>= 1) {
        if (n & 1) {
            ans = Math.max(ans, cur - pre);
            pre = cur;
        }
        ++cur;
    }
    return ans;
};
