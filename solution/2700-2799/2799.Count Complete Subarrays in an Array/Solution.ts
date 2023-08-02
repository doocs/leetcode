function countCompleteSubarrays(nums: number[]): number {
    const d: Map<number, number> = new Map();
    for (const x of nums) {
        d.set(x, (d.get(x) ?? 0) + 1);
    }
    const cnt = d.size;
    d.clear();
    const n = nums.length;
    let ans = 0;
    let i = 0;
    for (let j = 0; j < n; ++j) {
        d.set(nums[j], (d.get(nums[j]) ?? 0) + 1);
        while (d.size === cnt) {
            ans += n - j;
            d.set(nums[i], d.get(nums[i])! - 1);
            if (d.get(nums[i]) === 0) {
                d.delete(nums[i]);
            }
            ++i;
        }
    }
    return ans;
}
