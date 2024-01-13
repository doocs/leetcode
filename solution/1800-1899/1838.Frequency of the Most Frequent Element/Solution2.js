/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var maxFrequency = function (nums, k) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const check = cnt => {
        for (let i = 0; i < n + 1 - cnt; ++i) {
            const j = i + cnt - 1;
            if (nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    };
    let left = 1;
    let right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
