function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const res = [];
    const list = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...list]);
            return;
        }
        list.push(nums[i]);
        dfs(i + 1);
        list.pop();
        dfs(i + 1);
    };
    dfs(0);
    return res;
}
