function widestPairOfIndices(nums1: number[], nums2: number[]): number {
    const d: Map<number, number> = new Map();
    d.set(0, -1);
    const n: number = nums1.length;
    let s: number = 0;
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        s += nums1[i] - nums2[i];
        if (d.has(s)) {
            ans = Math.max(ans, i - (d.get(s) as number));
        } else {
            d.set(s, i);
        }
    }
    return ans;
}
