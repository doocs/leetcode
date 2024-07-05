/**
 * @param {number[]} nums
 * @return {number}
 */
var minDifference = function (nums) {
    if (nums.length < 5) {
        return 0;
    }
    nums.sort((a, b) => a - b);
    let ans = Number.POSITIVE_INFINITY;
    for (let i = 0; i < 4; i++) {
        ans = Math.min(ans, nums.at(i - 4) - nums[i]);
    }
    return ans;
};
