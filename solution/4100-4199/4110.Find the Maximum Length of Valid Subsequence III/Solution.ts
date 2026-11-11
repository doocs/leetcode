function maximumLength(nums: number[], k: number): number {
    const n = nums.length;
    const f: number[] = new Array(n).fill(1);
    const last = new Map<number, number>();
    let ans = 1;
    for (let i = 0; i < n; i++) {
        const x = nums[i] % k;
        const y = (k - x) % k;
        if (last.has(y)) {
            const j = last.get(y)!;
            f[i] = Math.max(f[i], f[j] + 1);
            ans = Math.max(ans, f[i]);
        }
        last.set(x, i);
    }
    return ans;
}
