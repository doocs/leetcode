/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
var hasIncreasingSubarrays = function (nums, k) {
    const n = nums.length;
    let [mx, pre, cur] = [0, 0, 0];
    for (let i = 0; i < n; ++i) {
        ++cur;
        if (i === n - 1 || nums[i] >= nums[i + 1]) {
            mx = Math.max(mx, cur >> 1, Math.min(pre, cur));
            pre = cur;
            cur = 0;
        }
    }
    return mx >= k;
};
