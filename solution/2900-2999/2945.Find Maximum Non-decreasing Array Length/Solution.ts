function findMaximumLength(nums: number[]): number {
    const n = nums.length;
    const f: number[] = Array(n + 1).fill(0);
    const pre: number[] = Array(n + 2).fill(0);
    const s: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        s[i] = s[i - 1] + nums[i - 1];
    }
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 1; i <= n; ++i) {
        pre[i] = Math.max(pre[i], pre[i - 1]);
        f[i] = f[pre[i]] + 1;
        const j = search(s, s[i] * 2 - s[pre[i]]);
        pre[j] = i;
    }
    return f[n];
}
