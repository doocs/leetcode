/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function (nums, k) {
    if (!nums.length || !k) return [];
    if (k === 1) return nums;
    let res = [];
    let tmpMax = -Infinity;
    let len = nums.length;
    let window = [];
    for (let i = 0; i < k; i++) {
        tmpMax = Math.max(nums[i], tmpMax);
        window.push(nums[i]);
    }
    res.push(tmpMax);
    for (let i = k; i < len; i++) {
        let a = window.shift();
        window.push(nums[i]);
        if (nums[i] > tmpMax) {
            tmpMax = nums[i];
        } else if (tmpMax === a) {
            tmpMax = Math.max(...window);
        }
        res.push(tmpMax);
    }
    return res;
};
