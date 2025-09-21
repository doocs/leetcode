/**
 * @param {number[]} nums
 * @param {number} a
 * @param {number} b
 * @param {number} c
 * @return {number[]}
 */
var sortTransformedArray = function (nums, a, b, c) {
    const f = x => a * x * x + b * x + c;
    const n = nums.length;
    let [i, j] = [0, n - 1];
    const ans = Array(n);
    for (let k = 0; k < n; ++k) {
        const y1 = f(nums[i]);
        const y2 = f(nums[j]);
        if (a > 0) {
            if (y1 > y2) {
                ans[n - k - 1] = y1;
                ++i;
            } else {
                ans[n - k - 1] = y2;
                --j;
            }
        } else {
            if (y1 > y2) {
                ans[k] = y2;
                --j;
            } else {
                ans[k] = y1;
                ++i;
            }
        }
    }
    return ans;
};
