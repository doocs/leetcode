function minimumTime(nums1: number[], nums2: number[], x: number): number {
    const n = nums1.length;
    const f: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    const nums: number[][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => a[1] - b[1]);
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j > 0) {
                const [a, b] = nums[i - 1];
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + a + b * j);
            }
        }
    }
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    for (let j = 0; j <= n; ++j) {
        if (s1 + s2 * j - f[n][j] <= x) {
            return j;
        }
    }
    return -1;
}
