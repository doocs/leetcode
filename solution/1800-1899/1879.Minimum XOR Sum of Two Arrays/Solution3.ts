function minimumXORSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const f: number[] = Array(1 << n).fill(1 << 30);
    f[0] = 0;
    for (let i = 0; i < 1 << n; ++i) {
        const k = bitCount(i) - 1;
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                f[i] = Math.min(f[i], f[i ^ (1 << j)] + (nums1[k] ^ nums2[j]));
            }
        }
    }
    return f[(1 << n) - 1];
}

function bitCount(i: number): number {
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    i = (i + (i >>> 4)) & 0x0f0f0f0f;
    i = i + (i >>> 8);
    i = i + (i >>> 16);
    return i & 0x3f;
}
