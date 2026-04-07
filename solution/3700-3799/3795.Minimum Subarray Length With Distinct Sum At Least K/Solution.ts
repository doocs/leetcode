function minLength(nums: number[], k: number): number {
    const n = nums.length;
    let ans = n + 1;
    const cnt = new Map<number, number>();
    let l = 0;
    let s = 0;
    for (let r = 0; r < n; ++r) {
        cnt.set(nums[r], (cnt.get(nums[r]) ?? 0) + 1);
        if (cnt.get(nums[r]) === 1) {
            s += nums[r];
        }
        while (s >= k) {
            ans = Math.min(ans, r - l + 1);
            cnt.set(nums[l], (cnt.get(nums[l]) ?? 0) - 1);
            if (cnt.get(nums[l]) === 0) {
                s -= nums[l];
            }
            ++l;
        }
    }
    return ans > n ? -1 : ans;
}
