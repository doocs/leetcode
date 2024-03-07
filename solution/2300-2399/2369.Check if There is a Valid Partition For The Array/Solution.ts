function validPartition(nums: number[]): boolean {
    const n = nums.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): boolean => {
        if (i >= n) {
            return true;
        }
        if (f[i] !== -1) {
            return f[i] === 1;
        }
        const a = i + 1 < n && nums[i] == nums[i + 1];
        const b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        const c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        f[i] = (a && dfs(i + 2)) || ((b || c) && dfs(i + 3)) ? 1 : 0;
        return f[i] == 1;
    };
    return dfs(0);
}
