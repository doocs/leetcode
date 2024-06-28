function intersection(nums1: number[], nums2: number[]): number[] {
    const s = new Set(nums1);
    return [...new Set(nums2.filter(x => s.has(x)))];
}
