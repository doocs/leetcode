/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
    let len = nums.length;
    let res = [];
    if (len < 4) return [];
    nums.sort((a, b) => a - b);
    for (i = 0; i < len - 3; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        if (nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) continue;
        if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
        for (j = i + 1; j < len - 2; j++) {
            if (j > i + 1 && nums[j] === nums[j - 1]) continue;
            let left = j + 1, right = len - 1;
            while (left < right) {
                if (nums[i] + nums[j] + nums[left] + nums[right] === target) {
                    res.push([nums[i], nums[j], nums[left], nums[right]]);
                    while (nums[left] === nums[left + 1]) left++;
                    left++;
                    while (nums[right] === nums[right - 1]) right--;
                    right--;
                    continue;
                } else if (nums[i] + nums[j] + nums[left] + nums[right] > target) {
                    right--;
                    continue;
                } else {
                    left++;
                    continue;
                }
            }
        }
    }
    return res;
};