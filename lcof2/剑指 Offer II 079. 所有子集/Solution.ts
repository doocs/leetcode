function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const ans = [];
    const dfs = (i: number, t: number[]) => {
        ans.push([...t]);
        while (i < n) {
            t.push(nums[i++]);
            dfs(i, t);
            t.pop();
        }
    };
    dfs(0, []);
    return ans;
}
