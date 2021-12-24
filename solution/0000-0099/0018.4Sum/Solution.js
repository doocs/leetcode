/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[][]}
 */
var fourSum = function (nums, target) {
    const n = nums.length;
    if (n < 4) return [];
    let res = [];
    nums.sort((a, b) => a - b);
    for (let i = 0; i < n - 3; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;
        for (let j = i + 1; j < n - 2; ++j) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;
            let k = j + 1;
            let l = n - 1;
            while (k < l) {
                if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                    res.push([nums[i], nums[j], nums[k], nums[l]]);
                    ++k;
                    --l;
                    while (k < n && nums[k] == nums[k - 1]) ++k;
                    while (l > j && nums[l] == nums[l + 1]) --l;
                } else if (nums[i] + nums[j] + nums[k] + nums[l] < target) {
                    ++k;
                } else {
                    --l;
                }
            }
        }
    }
    return res;
};
