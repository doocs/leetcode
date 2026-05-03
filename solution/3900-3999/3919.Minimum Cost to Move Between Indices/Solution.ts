function minCost(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const s1: number[] = new Array(n).fill(0);
    const s2: number[] = new Array(n).fill(0);
    for (let i = 1; i < n; i++) {
        const c1 =
            i > 1 && nums[i - 1] - nums[i - 2] <= nums[i] - nums[i - 1] ? nums[i] - nums[i - 1] : 1;
        const c2 =
            i < n - 1 && nums[i] - nums[i - 1] > nums[i + 1] - nums[i] ? nums[i] - nums[i - 1] : 1;
        s1[i] = s1[i - 1] + c1;
        s2[i] = s2[i - 1] + c2;
    }
    const m = queries.length;
    const ans: number[] = new Array(m);
    for (let i = 0; i < m; i++) {
        const l = queries[i][0];
        const r = queries[i][1];
        ans[i] = l < r ? s1[r] - s1[l] : s2[l] - s2[r];
    }
    return ans;
}
