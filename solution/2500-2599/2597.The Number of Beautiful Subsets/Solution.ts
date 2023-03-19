function beautifulSubsets(nums: number[], k: number): number {
    let ans: number = -1;
    const cnt: number[] = new Array(1010).fill(0);
    const n: number = nums.length;
    const dfs = (i: number) => {
        if (i >= n) {
            ++ans;
            return;
        }
        dfs(i + 1);
        const ok1: boolean = nums[i] + k >= 1010 || cnt[nums[i] + k] === 0;
        const ok2: boolean = nums[i] - k < 0 || cnt[nums[i] - k] === 0;
        if (ok1 && ok2) {
            ++cnt[nums[i]];
            dfs(i + 1);
            --cnt[nums[i]];
        }
    };
    dfs(0);
    return ans;
}
