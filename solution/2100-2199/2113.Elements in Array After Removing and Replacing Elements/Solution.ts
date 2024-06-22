function elementInNums(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const m = queries.length;
    const ans: number[] = Array(m).fill(-1);
    for (let j = 0; j < m; ++j) {
        let [t, i] = queries[j];
        t %= 2 * n;
        if (t < n && i < n - t) {
            ans[j] = nums[i + t];
        } else if (t >= n && i < t - n) {
            ans[j] = nums[i];
        }
    }
    return ans;
}
