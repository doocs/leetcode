function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const f: boolean[] = Array(n + 1).fill(false);
    f[n] = true;
    for (let i = n - 1; i >= 0; --i) {
        const a = i + 1 < n && nums[i] == nums[i + 1];
        const b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        const c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        f[i] = (a && f[i + 2]) || ((b || c) && f[i + 3]);
    }
    return f[0];
}
