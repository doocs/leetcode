function minSum(nums1: number[], nums2: number[]): number {
    let [s1, s2] = [0, 0];
    let hasZero = false;
    for (const x of nums1) {
        if (x === 0) {
            hasZero = true;
        }
        s1 += Math.max(x, 1);
    }
    for (const x of nums2) {
        s2 += Math.max(x, 1);
    }
    if (s1 > s2) {
        return minSum(nums2, nums1);
    }
    if (s1 === s2) {
        return s1;
    }
    return hasZero ? s2 : -1;
}
