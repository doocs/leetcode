function distance(nums: number[]): number[] {
    const n = nums.length;
    const ans = new Array(n).fill(0);
    const d = new Map<number, number[]>();
    for (let i = 0; i < n; ++i) {
        if (!d.has(nums[i])) {
            d.set(nums[i], []);
        }
        d.get(nums[i])!.push(i);
    }
    for (const idx of d.values()) {
        const m = idx.length;
        let left = 0;
        let right = -1 * m * idx[0];
        for (const i of idx) {
            right += i;
        }
        for (let i = 0; i < m; ++i) {
            ans[idx[i]] = left + right;
            if (i + 1 < m) {
                left += (idx[i + 1] - idx[i]) * (i + 1);
                right -= (idx[i + 1] - idx[i]) * (m - i - 1);
            }
        }
    }
    return ans;
}
