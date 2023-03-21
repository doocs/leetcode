/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let ans = 0;
    let s = 1;
    for (let i = 0, j = 0; i < n; ++i) {
        s *= nums[i];
        while (j <= i && s >= k) {
            s = Math.floor(s / nums[j++]);
        }
        ans += i - j + 1;
    }
    return ans;
};
