function permuteUnique(nums: number[]): number[][] {
    const n = nums.length;
    const res: number[][] = [];
    const dfs = (i: number) => {
        if (i === n) {
            res.push([...nums]);
        }
        const set = new Set<number>();
        for (let j = i; j < n; j++) {
            if (set.has(nums[j])) {
                continue;
            }
            set.add(nums[j]);
            [nums[i], nums[j]] = [nums[j], nums[i]];
            dfs(i + 1);
            [nums[i], nums[j]] = [nums[j], nums[i]];
        }
    };
    dfs(0);
    return res;
}
