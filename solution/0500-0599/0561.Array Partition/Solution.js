/**
 * @param {number[]} nums
 * @return {number}
 */
var arrayPairSum = function (nums) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < nums.length; i += 2) {
        ans += nums[i];
    }
    return ans;
};
