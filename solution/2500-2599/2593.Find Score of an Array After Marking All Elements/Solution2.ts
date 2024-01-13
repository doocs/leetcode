function findScore(nums: number[]): number {
    const n = nums.length;
    const idx: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        idx[i] = i;
    }
    idx.sort((i, j) => (nums[i] == nums[j] ? i - j : nums[i] - nums[j]));
    const vis: boolean[] = new Array(n + 2).fill(false);
    let ans = 0;
    for (const i of idx) {
        if (!vis[i + 1]) {
            ans += nums[i];
            vis[i] = true;
            vis[i + 2] = true;
        }
    }
    return ans;
}
