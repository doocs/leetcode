/**
 * @param {number[]} nums
 * @return {number}
 */
var maximumSumScore = function (nums) {
    const n = nums.length;
    let s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let ans = -Infinity;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.max(s[i + 1], s[n] - s[i]));
    }
    return ans;
};
