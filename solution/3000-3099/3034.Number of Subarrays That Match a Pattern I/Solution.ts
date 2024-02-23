function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const f = (a: number, b: number) => (a === b ? 0 : a < b ? 1 : -1);
    const n = nums.length;
    const m = pattern.length;
    let ans = 0;
    for (let i = 0; i < n - m; ++i) {
        let ok = 1;
        for (let k = 0; k < m && ok; ++k) {
            if (f(nums[i + k], nums[i + k + 1]) !== pattern[k]) {
                ok = 0;
            }
        }
        ans += ok;
    }
    return ans;
}
