function distinctDifferenceArray(nums: number[]): number[] {
    const n = nums.length;
    const suf: number[] = new Array(n + 1).fill(0);
    const s: Set<number> = new Set();
    for (let i = n - 1; i >= 0; --i) {
        s.add(nums[i]);
        suf[i] = s.size;
    }
    s.clear();
    const ans: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        s.add(nums[i]);
        ans[i] = s.size - suf[i + 1];
    }
    return ans;
}
