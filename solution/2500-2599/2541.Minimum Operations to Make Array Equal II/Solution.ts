function minOperations(nums1: number[], nums2: number[], k: number): number {
    const n = nums1.length;
    if (k === 0) {
        return nums1.every((v, i) => v === nums2[i]) ? 0 : -1;
    }
    let sum1 = 0;
    let sum2 = 0;
    for (let i = 0; i < n; i++) {
        const diff = nums1[i] - nums2[i];
        sum1 += diff;
        if (diff % k !== 0) {
            return -1;
        }
        sum2 += Math.abs(diff);
    }
    if (sum1 !== 0) {
        return -1;
    }
    return sum2 / (k * 2);
}
