/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    const m = new Map();
    for (let i = 0; i < nums.length; ++i) {
        const v = nums[i];
        const x = target - v;
        if (m.has(x)) {
            return [m.get(x), i];
        }
        m.set(v, i);
    }
    return [];
};
