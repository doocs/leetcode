function maxStrength(nums: number[]): number {
    let ans = -Infinity;
    const n = nums.length;
    for (let i = 1; i < 1 << n; ++i) {
        let t = 1;
        for (let j = 0; j < n; ++j) {
            if ((i >> j) & 1) {
                t *= nums[j];
            }
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
