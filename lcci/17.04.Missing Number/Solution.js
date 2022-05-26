/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function (nums) {
    let res;
    for (let i = 0; i < nums.length; i++) {
        res = res ^ nums[i] ^ (i + 1);
    }
    return res;
};
