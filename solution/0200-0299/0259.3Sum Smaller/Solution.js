/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumSmaller = function (nums, target) {
    let len = nums.length;
    if (len < 3) return 0;
    nums.sort((a, b) => a - b)
    let res = 0;
    for (let i = 0; i < len - 2; i++) {
        let left = i + 1, right = len - 1;
        if (nums[i] + nums[left] + nums[i + 2] >= target) break;
        while (left < right) {
            if (nums[i] + nums[left] + nums[right] < target) {
                res += (right - left);
                left++;
                continue;
            } else {
                right--;
                continue;
            }
        }
    }
    return res;
};