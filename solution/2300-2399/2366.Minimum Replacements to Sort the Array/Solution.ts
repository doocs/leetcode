function minimumReplacement(nums: number[]): number {
    const n = nums.length;
    let mx = nums[n - 1];
    let ans = 0;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] <= mx) {
            mx = nums[i];
            continue;
        }
        const k = Math.ceil(nums[i] / mx);
        ans += k - 1;
        mx = Math.floor(nums[i] / k);
    }
    return ans;
}
