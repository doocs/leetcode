function minNumber(nums1: number[], nums2: number[]): number {
    let mask1: number = 0;
    let mask2: number = 0;
    for (const x of nums1) {
        mask1 |= 1 << x;
    }
    for (const x of nums2) {
        mask2 |= 1 << x;
    }
    const mask = mask1 & mask2;
    if (mask !== 0) {
        return numberOfTrailingZeros(mask);
    }
    const a = numberOfTrailingZeros(mask1);
    const b = numberOfTrailingZeros(mask2);
    return Math.min(a * 10 + b, b * 10 + a);
}

function numberOfTrailingZeros(i: number): number {
    let y = 0;
    if (i === 0) {
        return 32;
    }
    let n = 31;
    y = i << 16;
    if (y != 0) {
        n = n - 16;
        i = y;
    }
    y = i << 8;
    if (y != 0) {
        n = n - 8;
        i = y;
    }
    y = i << 4;
    if (y != 0) {
        n = n - 4;
        i = y;
    }
    y = i << 2;
    if (y != 0) {
        n = n - 2;
        i = y;
    }
    return n - ((i << 1) >>> 31);
}
