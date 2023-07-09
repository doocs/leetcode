function maximumJumps(nums: number[], target: number): number {
    const n = nums.length;
    const f: number[] = Array(n).fill(-1);
    const dfs = (i: number): number => {
        if (i === n - 1) {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        f[i] = -(1 << 30);
        for (let j = i + 1; j < n; ++j) {
            if (Math.abs(nums[i] - nums[j]) <= target) {
                f[i] = Math.max(f[i], 1 + dfs(j));
            }
        }
        return f[i];
    };
    const ans = dfs(0);
    return ans < 0 ? -1 : ans;
}
