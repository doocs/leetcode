function isArraySpecial(nums: number[], queries: number[][]): boolean[] {
    const n = nums.length;
    const d: number[] = Array.from({ length: n }, (_, i) => i);
    for (let i = 1; i < n; ++i) {
        if (nums[i] % 2 !== nums[i - 1] % 2) {
            d[i] = d[i - 1];
        }
    }
    return queries.map(([from, to]) => d[to] <= from);
}
