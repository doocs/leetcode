/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange = function (nums) {
    let left = 0;
    let right = nums.length - 1;
    while (left < right) {
        let c = nums[left];
        nums[left] = nums[right];
        nums[right] = c;
        while (nums[left] % 2) {
            left++;
        }
        while (nums[right] % 2 === 0) {
            right--;
        }
    }
    return nums;
};
