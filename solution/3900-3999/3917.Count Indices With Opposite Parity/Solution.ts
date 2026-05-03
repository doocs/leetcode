function countOppositeParity(nums: number[]): number[] {
    const cnt = Array<number>(2).fill(0);
    for (const x of nums) {
        ++cnt[x & 1];
    }
    const n = nums.length;
    const ans = Array<number>(n).fill(0);
    for (let i = 0; i < n; ++i) {
        --cnt[nums[i] & 1];
        ans[i] = cnt[(nums[i] & 1) ^ 1];
    }
    return ans;
}
