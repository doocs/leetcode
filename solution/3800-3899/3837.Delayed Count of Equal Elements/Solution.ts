function delayedCount(nums: number[], k: number): number[] {
    const n = nums.length;
    const cnt = new Map<number, number>();
    const ans = Array(n).fill(0);
    for (let i = n - k - 2; i >= 0; i--) {
        cnt.set(nums[i + k + 1], (cnt.get(nums[i + k + 1]) ?? 0) + 1);
        ans[i] = cnt.get(nums[i]) ?? 0;
    }
    return ans;
}
