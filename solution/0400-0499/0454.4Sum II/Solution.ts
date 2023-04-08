function fourSumCount(
    nums1: number[],
    nums2: number[],
    nums3: number[],
    nums4: number[],
): number {
    const cnt: Map<number, number> = new Map();
    for (const a of nums1) {
        for (const b of nums2) {
            const x = a + b;
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    let ans = 0;
    for (const c of nums3) {
        for (const d of nums4) {
            const x = c + d;
            ans += cnt.get(-x) || 0;
        }
    }
    return ans;
}
