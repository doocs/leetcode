function minAbsDiff(grid: number[][], k: number): number[][] {
    const m = grid.length;
    const n = grid[0].length;
    const ans: number[][] = Array.from({ length: m - k + 1 }, () => Array(n - k + 1).fill(0));
    for (let i = 0; i <= m - k; i++) {
        for (let j = 0; j <= n - k; j++) {
            const nums: number[] = [];
            for (let x = i; x < i + k; x++) {
                for (let y = j; y < j + k; y++) {
                    nums.push(grid[x][y]);
                }
            }
            nums.sort((a, b) => a - b);
            let d = Number.MAX_SAFE_INTEGER;
            for (let t = 1; t < nums.length; t++) {
                if (nums[t] !== nums[t - 1]) {
                    d = Math.min(d, Math.abs(nums[t] - nums[t - 1]));
                }
            }
            ans[i][j] = d === Number.MAX_SAFE_INTEGER ? 0 : d;
        }
    }
    return ans;
}
