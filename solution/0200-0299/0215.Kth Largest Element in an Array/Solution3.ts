function findKthLargest(nums: number[], k: number): number {
    const cnt: Record<number, number> = {};
    for (const x of nums) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const m = Math.max(...nums);
    for (let i = m; ; --i) {
        k -= cnt[i] || 0;
        if (k <= 0) {
            return i;
        }
    }
}
