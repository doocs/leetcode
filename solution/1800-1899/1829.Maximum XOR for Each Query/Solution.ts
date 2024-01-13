function getMaximumXor(nums: number[], maximumBit: number): number[] {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
    const n = nums.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        const x = nums[n - i - 1];
        let k = 0;
        for (let j = maximumBit - 1; j >= 0; --j) {
            if (((xs >> j) & 1) == 0) {
                k |= 1 << j;
            }
        }
        ans[i] = k;
        xs ^= x;
    }
    return ans;
}
