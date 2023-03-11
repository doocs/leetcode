/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    let ans = 0;
    for (let i = 1; i <= nums.length; ++i) {
        ans ^= i ^ nums[i - 1];
    }
    return ans;
};
