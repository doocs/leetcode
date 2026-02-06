function minRemoval(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = n;
    let r = 0;
    for (let l = 0; l < n; l++) {
        while (r < n && nums[r] <= nums[l] * k) {
            r++;
        }
        ans = Math.min(ans, n - (r - l));
    }
    return ans;
}
