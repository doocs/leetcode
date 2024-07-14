function findDifference(nums1: number[], nums2: number[]): number[][] {
    const s1: Set<number> = new Set(nums1);
    const s2: Set<number> = new Set(nums2);
    nums1.forEach(num => s2.delete(num));
    nums2.forEach(num => s1.delete(num));
    return [Array.from(s1), Array.from(s2)];
}
