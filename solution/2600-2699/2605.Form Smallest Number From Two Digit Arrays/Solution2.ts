function minNumber(nums1: number[], nums2: number[]): number {
    const s1: boolean[] = new Array(10).fill(false);
    const s2: boolean[] = new Array(10).fill(false);
    for (const x of nums1) {
        s1[x] = true;
    }
    for (const x of nums2) {
        s2[x] = true;
    }
    let a = 0;
    let b = 0;
    for (let i = 1; i < 10; ++i) {
        if (s1[i] && s2[i]) {
            return i;
        }
        if (a === 0 && s1[i]) {
            a = i;
        }
        if (b === 0 && s2[i]) {
            b = i;
        }
    }
    return Math.min(a * 10 + b, b * 10 + a);
}
