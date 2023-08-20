function longestEqualSubarray(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let mx = 0;
    let l = 0;
    for (let r = 0; r < nums.length; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        mx = Math.max(mx, cnt.get(nums[r])!);
        if (r - l + 1 - mx > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            ++l;
        }
    }
    return mx;
}
