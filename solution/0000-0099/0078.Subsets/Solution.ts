function subsets(nums: number[]): number[][] {
    const ans: number[][] = [];
    const t: number[] = [];
    const dfs = (i: number) => {
        if (i === nums.length) {
            ans.push(t.slice());
            return;
        }
        dfs(i + 1);
        t.push(nums[i]);
        dfs(i + 1);
        t.pop();
    };
    dfs(0);
    return ans;
}
