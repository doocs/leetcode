function minAbsoluteDifference(nums: number[]): number {
    const n = nums.length;
    let ans = n + 1;
    const last = Array(3).fill(-ans);

    for (let i = 0; i < n; ++i) {
        const x = nums[i];
        if (x) {
            ans = Math.min(ans, i - last[3 - x]);
            last[x] = i;
        }
    }

    return ans > n ? -1 : ans;
}
