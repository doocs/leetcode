function isZeroArray(nums: number[], queries: number[][]): boolean {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    for (const [l, r] of queries) {
        ++d[l];
        --d[r + 1];
    }
    for (let i = 0, s = 0; i < n; ++i) {
        s += d[i];
        if (nums[i] > s) {
            return false;
        }
    }
    return true;
}
