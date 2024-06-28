function intersect(nums1: number[], nums2: number[]): number[] {
    const cnt: Record<number, number> = {};
    for (const x of nums1) {
        cnt[x] = (cnt[x] || 0) + 1;
    }
    const ans: number[] = [];
    for (const x of nums2) {
        if (cnt[x]-- > 0) {
            ans.push(x);
        }
    }
    return ans;
}
