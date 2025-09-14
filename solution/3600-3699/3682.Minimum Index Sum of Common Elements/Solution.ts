function minimumSum(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const inf = 1 << 30;
    const d = new Map<number, number>();
    for (let i = 0; i < n; i++) {
        if (!d.has(nums2[i])) {
            d.set(nums2[i], i);
        }
    }
    let ans = inf;
    for (let i = 0; i < n; i++) {
        if (d.has(nums1[i])) {
            ans = Math.min(ans, i + (d.get(nums1[i]) as number));
        }
    }
    return ans === inf ? -1 : ans;
}
