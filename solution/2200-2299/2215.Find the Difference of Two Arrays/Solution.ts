function findDifference(nums1: number[], nums2: number[]): number[][] {
    return [
        [...new Set<number>(nums1.filter(v => !nums2.includes(v)))],
        [...new Set<number>(nums2.filter(v => !nums1.includes(v)))],
    ];
}
