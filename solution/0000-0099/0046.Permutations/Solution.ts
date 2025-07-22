function permute(nums: number[]): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    const vis: boolean[] = Array(n).fill(false);
    const t: number[] = Array(n).fill(0);
    const dfs = (i: number) => {
        if (i >= n) {
            ans.push(t.slice());
            return;
        }
        for (let j = 0; j < n; ++j) {
            if (!vis[j]) {
                vis[j] = true;
                t[i] = nums[j];
                dfs(i + 1);
                vis[j] = false;
            }
        }
    };
    dfs(0);
    return ans;
}
