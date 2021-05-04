/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var threeSum = function (nums) {
    let len = nums.length;
    if (len < 3) return [];
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < len - 2; i++) {
        if (nums[i] > 0) break;
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        let left = i + 1, right = len - 1;
        while (left < right) {
            if (nums[i] + nums[left] + nums[right] === 0) {
                res.push([nums[i], nums[left], nums[right]]);
                while (nums[left] === nums[left + 1]) left++;
                left++;
                while (nums[right] === nums[right - 1]) right--;
                right--;
                continue;
            } else if (nums[i] + nums[left] + nums[right] > 0) {
                right--;
                continue;
            } else {
                left++;
                continue;
            }
        }
    }
    return res;
};