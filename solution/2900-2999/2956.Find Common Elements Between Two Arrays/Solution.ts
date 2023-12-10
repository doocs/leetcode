function findIntersectionValues(nums1: number[], nums2: number[]): number[] {
    const s1: number[] = Array(101).fill(0);
    const s2: number[] = Array(101).fill(0);
    for (const x of nums1) {
        s1[x] = 1;
    }
    for (const x of nums2) {
        s2[x] = 1;
    }
    const ans: number[] = Array(2).fill(0);
    for (const x of nums1) {
        ans[0] += s2[x];
    }
    for (const x of nums2) {
        ans[1] += s1[x];
    }
    return ans;
}
