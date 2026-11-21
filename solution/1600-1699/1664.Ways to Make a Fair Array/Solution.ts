function waysToMakeFair(nums: number[]): number {
    const s = nums.reduce((a, b) => a + b, 0);
    let r0 = 0;
    for (let i = 0; i < nums.length; i += 2) {
        r0 += nums[i];
    }
    const r = [r0, s - r0];
    const l = [0, 0];
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        r[i % 2] -= x;
        if (l[0] + r[1] === l[1] + r[0]) {
            ++ans;
        }
        l[i % 2] += x;
    }
    return ans;
}
