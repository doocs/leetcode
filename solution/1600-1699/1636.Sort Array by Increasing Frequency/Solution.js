/**
 * @param {number[]} nums
 * @return {number[]}
 */
var frequencySort = function (nums) {
    const m = new Map();
    for (let i = 0; i < nums.length; i++) {
        m.set(nums[i], (m.get(nums[i]) || 0) + 1);
    }
    nums.sort((a, b) => (m.get(a) != m.get(b) ? m.get(a) - m.get(b) : b - a));
    return nums;
};
