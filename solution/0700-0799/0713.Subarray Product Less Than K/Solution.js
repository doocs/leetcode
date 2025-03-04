/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let [ans, l, p] = [0, 0, 1];
    for (let r = 0; r < n; ++r) {
        p *= nums[r];
        while (l <= r && p >= k) {
            p /= nums[l++];
        }
        ans += r - l + 1;
    }
    return ans;
};
