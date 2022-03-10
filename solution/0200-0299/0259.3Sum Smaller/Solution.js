/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var threeSumSmaller = function (nums, target) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0, n = nums.length; i < n; ++i) {
        let j = i + 1;
        let k = n - 1;
        while (j < k) {
            s = nums[i] + nums[j] + nums[k];
            if (s >= target) {
                --k;
            } else {
                ans += k - j;
                ++j;
            }
        }
    }
    return ans;
};
