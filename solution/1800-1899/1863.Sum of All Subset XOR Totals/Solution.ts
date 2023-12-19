function subsetXORSum(nums: number[]): number {
    let ans = 0;
    const n = nums.length;
    const dfs = (i: number, s: number) => {
        if (i >= n) {
            ans += s;
            return;
        }
        dfs(i + 1, s);
        dfs(i + 1, s ^ nums[i]);
    };
    dfs(0, 0);
    return ans;
}
