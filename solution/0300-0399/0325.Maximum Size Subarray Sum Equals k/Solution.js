/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxSubArrayLen = function (nums, k) {
    const d = new Map();
    d.set(0, -1);
    let ans = 0;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        if (d.has(s - k)) {
            ans = Math.max(ans, i - d.get(s - k));
        }
        if (!d.has(s)) {
            d.set(s, i);
        }
    }
    return ans;
};
