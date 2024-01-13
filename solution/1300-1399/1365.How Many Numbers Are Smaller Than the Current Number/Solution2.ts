function smallerNumbersThanCurrent(nums: number[]): number[] {
    const cnt: number[] = new Array(102).fill(0);
    for (const x of nums) {
        ++cnt[x + 1];
    }
    for (let i = 1; i < cnt.length; ++i) {
        cnt[i] += cnt[i - 1];
    }
    const n = nums.length;
    const ans: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = cnt[nums[i]];
    }
    return ans;
}
