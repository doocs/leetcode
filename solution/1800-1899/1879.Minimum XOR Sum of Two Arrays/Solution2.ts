function minimumXORSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (const x of nums1) {
        for (let j = (1 << n) - 1; ~j; --j) {
            for (let k = 0; k < n; ++k) {
                if (((j >> k) & 1) === 1) {
                    f[j] = Math.min(f[j], f[j ^ (1 << k)] + (x ^ nums2[k]));
                }
            }
        }
    }
    return f[(1 << n) - 1];
}
