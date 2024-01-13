function longestAlternatingSubarray(nums: number[], threshold: number): number {
    const n = nums.length;
    let ans = 0;
    for (let l = 0; l < n; ++l) {
        if (nums[l] % 2 === 0 && nums[l] <= threshold) {
            let r = l + 1;
            while (r < n && nums[r] % 2 !== nums[r - 1] % 2 && nums[r] <= threshold) {
                ++r;
            }
            ans = Math.max(ans, r - l);
        }
    }
    return ans;
}
