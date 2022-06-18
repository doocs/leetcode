function subsetsWithDup(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const t: number[] = [];
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...t]);
            return;
        }
        t.push(nums[i]);
        dfs(i + 1);
        const num = t.pop();
        while (i < n && nums[i] == num) {
            i++;
        }
        dfs(i);
    };
    dfs(0);
    return res;
}
