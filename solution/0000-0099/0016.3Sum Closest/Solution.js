/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumClosest = function (nums, target) {
    let len = nums.length;
    nums.sort((a, b) => a - b);
    let diff = Infinity;
    let res;
    for (let i = 0; i < len - 2; i++) {
        if (i > 0 && nums[i] === nums[i - 1]) continue;
        let left = i + 1,
            right = len - 1;
        let cur = nums[i] + nums[i + 1] + nums[i + 2];
        if (cur > target) {
            let newDiff = Math.abs(cur - target);
            if (newDiff < diff) {
                diff = newDiff;
                res = cur;
            }
            break;
        }
        while (left < right) {
            cur = nums[i] + nums[left] + nums[right];
            if (cur === target) return target;
            let newDiff = Math.abs(cur - target);
            if (newDiff < diff) {
                diff = newDiff;
                res = cur;
            }
            if (cur < target) {
                while (nums[left] === nums[left + 1]) left++;
                left++;
                continue;
            } else {
                while (nums[right] === nums[right - 1]) right--;
                right--;
                continue;
            }
        }
    }
    return res;
};
