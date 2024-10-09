function minChanges(nums: number[], k: number): number {
    const d: number[] = Array(k + 2).fill(0);
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        const x = Math.min(nums[i], nums[n - 1 - i]);
        const y = Math.max(nums[i], nums[n - 1 - i]);
        d[0] += 1;
        d[y - x] -= 1;
        d[y - x + 1] += 1;
        d[Math.max(y, k - x) + 1] -= 1;
        d[Math.max(y, k - x) + 1] += 2;
    }
    let [ans, s] = [n, 0];
    for (const x of d) {
        s += x;
        ans = Math.min(ans, s);
    }
    return ans;
}
