/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToSplit = function (nums) {
    const mod = 1e9 + 7;
    const n = nums.length;
    const s = new Array(n).fill(nums[0]);
    for (let i = 1; i < n; ++i) {
        s[i] = s[i - 1] + nums[i];
    }
    function search(s, x, left, right) {
        while (left < right) {
            const mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    let ans = 0;
    for (let i = 0; i < n - 2; ++i) {
        const j = search(s, s[i] << 1, i + 1, n - 1);
        const k = search(s, ((s[n - 1] + s[i]) >> 1) + 1, j, n - 1);
        ans = (ans + k - j) % mod;
    }
    return ans;
};
