export function resultsArray(nums, k) {
    const n = nums.length;
    const ans = [];

    for (let i = 0, j = 0; i < n; i++) {
        if (i && nums[i - 1] + 1 !== nums[i]) j = i;
        if (i >= k - 1) {
            ans.push(i - k + 1 < j ? -1 : nums[i]);
        }
    }

    return ans;
}
