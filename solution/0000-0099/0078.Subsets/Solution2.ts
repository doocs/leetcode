function subsets(nums: number[]): number[][] {
    const n = nums.length;
    const ans: number[][] = [];
    for (let mask = 0; mask < 1 << n; ++mask) {
        const t: number[] = [];
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                t.push(nums[i]);
            }
        }
        ans.push(t);
    }
    return ans;
}
