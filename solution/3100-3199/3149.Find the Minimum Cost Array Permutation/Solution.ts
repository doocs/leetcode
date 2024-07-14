function findPermutation(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = [];
    const f: number[][] = Array.from({ length: 1 << n }, () => Array(n).fill(-1));
    const dfs = (mask: number, pre: number): number => {
        if (mask === (1 << n) - 1) {
            return Math.abs(pre - nums[0]);
        }
        if (f[mask][pre] !== -1) {
            return f[mask][pre];
        }
        let res = Infinity;
        for (let cur = 1; cur < n; ++cur) {
            if (((mask >> cur) & 1) ^ 1) {
                res = Math.min(res, Math.abs(pre - nums[cur]) + dfs(mask | (1 << cur), cur));
            }
        }
        return (f[mask][pre] = res);
    };
    const g = (mask: number, pre: number) => {
        ans.push(pre);
        if (mask === (1 << n) - 1) {
            return;
        }
        const res = dfs(mask, pre);
        for (let cur = 1; cur < n; ++cur) {
            if (((mask >> cur) & 1) ^ 1) {
                if (Math.abs(pre - nums[cur]) + dfs(mask | (1 << cur), cur) === res) {
                    g(mask | (1 << cur), cur);
                    break;
                }
            }
        }
    };
    g(1, 0);
    return ans;
}
