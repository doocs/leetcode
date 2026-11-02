function minMergedInteger(nums: number[]): number {
    let ans = Number.MAX_SAFE_INTEGER;
    for (let i = 0; i < nums.length; i += 2) {
        ans = Math.min(ans, Number(String(nums[i]) + nums[i + 1]));
    }
    return ans;
}
