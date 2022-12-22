/**
 * @param {string[]} operations
 * @return {number}
 */
var finalValueAfterOperations = function (operations) {
    let ans = 0;
    for (const s of operations) {
        ans += s[1] === '+' ? 1 : -1;
    }
    return ans;
};
