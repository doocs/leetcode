/**
 * @param {number[]} nums
 * @return {number[][]}
 */
 var threeSum = function(nums) {
    const n = nums.length;
    if (n < 3) return [];
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 2 && nums[i] <= 0; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            if (nums[i] + nums[j] + nums[k] === 0) {
                res.push([nums[i], nums[j], nums[k]]);
                ++j;
                --k;
                while (nums[j] === nums[j - 1]) ++j;
                while (nums[k] === nums[k + 1]) --k;
            } else if (nums[i] + nums[j] + nums[k] < 0) {
                ++j;
            } else {
                --k;
            }
        }
    }
    return res;
};