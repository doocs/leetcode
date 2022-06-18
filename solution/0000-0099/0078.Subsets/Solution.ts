function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...t]);
            return;
        }
        dfs(i + 1);
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
    };
    dfs(0);
    return res;
}
