function minimumAverageDifference(nums: number[]): number {
    const n = nums.length;
    let pre = 0;
    let suf = nums.reduce((a, b) => a + b);
    let ans = 0;
    let mi = suf;
    for (let i = 0; i < n; ++i) {
        pre += nums[i];
        suf -= nums[i];
        const a = Math.floor(pre / (i + 1));
        const b = n - i - 1 === 0 ? 0 : Math.floor(suf / (n - i - 1));
        const t = Math.abs(a - b);
        if (t < mi) {
            ans = i;
            mi = t;
        }
    }
    return ans;
}
