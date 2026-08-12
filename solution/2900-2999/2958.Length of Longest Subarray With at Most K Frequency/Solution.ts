function maxSubarrayLength(nums: number[], k: number): number {
    let ans = 0;
    const cnt = new Map<number, number>();
    for (let l = 0, r = 0; r < nums.length; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        while (cnt.get(nums[r])! > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            ++l;
        }
        ans = Math.max(ans, r - l + 1);
    }
    return ans;
}
