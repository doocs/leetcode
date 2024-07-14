function minOperations(nums: number[], x: number): number {
    const s = nums.reduce((acc, cur) => acc + cur, -x);
    let [mx, t] = [-1, 0];
    const n = nums.length;
    for (let i = 0, j = 0; i < n; ++i) {
        t += nums[i];
        while (t > s) {
            t -= nums[j++];
        }
        if (t === s) {
            mx = Math.max(mx, i - j + 1);
        }
    }
    return ~mx ? n - mx : -1;
}
