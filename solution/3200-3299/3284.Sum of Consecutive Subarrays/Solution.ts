function getSum(nums: number[]): number {
    const mod = 10 ** 9 + 7;
    let f = 1,
        g = 1;
    let s = nums[0],
        t = nums[0];
    let ans = nums[0];

    for (let i = 1; i < nums.length; i++) {
        const x = nums[i - 1];
        const y = nums[i];

        if (y - x === 1) {
            f++;
            s += f * y;
            ans = (ans + s) % mod;
        } else {
            f = 1;
            s = y;
        }

        if (y - x === -1) {
            g++;
            t += g * y;
            ans = (ans + t) % mod;
        } else {
            g = 1;
            t = y;
        }

        if (Math.abs(y - x) !== 1) {
            ans = (ans + y) % mod;
        }
    }

    return ans;
}
