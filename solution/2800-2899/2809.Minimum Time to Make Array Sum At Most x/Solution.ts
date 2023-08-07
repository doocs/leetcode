function minimumTime(nums1: number[], nums2: number[], x: number): number {
    const n = nums1.length;
    const f: number[] = new Array(n + 1).fill(0);
    const nums: number[][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => a[1] - b[1]);
    for (const [a, b] of nums) {
        for (let j = n; j > 0; --j) {
            f[j] = Math.max(f[j], f[j - 1] + a + b * j);
        }
    }
    const s1 = nums1.reduce((a, b) => a + b, 0);
    const s2 = nums2.reduce((a, b) => a + b, 0);
    for (let j = 0; j <= n; ++j) {
        if (s1 + s2 * j - f[j] <= x) {
            return j;
        }
    }
    return -1;
}
