function countMajoritySubarrays(nums: number[], target: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const cnt: Record<number, number> = {};
        for (let j = i; j < n; ++j) {
            const k = j - i + 1;
            cnt[nums[j]] = (cnt[nums[j]] || 0) + 1;
            if ((cnt[target] || 0) > k >> 1) {
                ++ans;
            }
        }
    }
    return ans;
}
