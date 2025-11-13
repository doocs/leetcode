function xorAfterQueries(nums: number[], queries: number[][]): number {
    const mod = 1e9 + 7;
    for (const [l, r, k, v] of queries) {
        for (let idx = l; idx <= r; idx += k) {
            nums[idx] = (nums[idx] * v) % mod;
        }
    }
    return nums.reduce((acc, x) => acc ^ x, 0);
}
