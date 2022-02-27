function permute(nums: number[]): number[][] {
    const res: number[][] = [];
    const dfs = (paths: number[]) => {
        if (paths.length === nums.length) {
            res.push(paths);
            return;
        }
        for (const num of nums) {
            if (!paths.includes(num)) {
                dfs(paths.concat(num));
            }
        }
    };
    dfs([]);
    return res;
}
