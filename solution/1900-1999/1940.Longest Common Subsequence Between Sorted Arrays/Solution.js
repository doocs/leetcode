/**
 * @param {number[][]} arrays
 * @return {number[]}
 */
var longestCommonSubsequence = function (arrays) {
    const cnt = Array(101).fill(0);
    for (const row of arrays) {
        for (const x of row) {
            ++cnt[x];
        }
    }
    const ans = [];
    for (let i = 0; i < 101; ++i) {
        if (cnt[i] === arrays.length) {
            ans.push(i);
        }
    }
    return ans;
};
