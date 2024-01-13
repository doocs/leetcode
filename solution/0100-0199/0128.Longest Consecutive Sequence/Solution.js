/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    const n = nums.length;
    if (n < 2) {
        return n;
    }
    nums.sort((a, b) => a - b);
    let ans = 1;
    let t = 1;
    for (let i = 1; i < n; ++i) {
        if (nums[i] === nums[i - 1]) {
            continue;
        }
        if (nums[i] === nums[i - 1] + 1) {
            ans = Math.max(ans, ++t);
        } else {
            t = 1;
        }
    }
    return ans;
};
