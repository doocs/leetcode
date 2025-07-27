function countMaxOrSubsets(nums: number[]): number {
    let ans = 0;
    const mx = nums.reduce((x, y) => x | y, 0);

    const dfs = (i: number, t: number) => {
        if (i === nums.length) {
            if (t === mx) {
                ans++;
            }
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    };

    dfs(0, 0);
    return ans;
}
