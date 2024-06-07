function numberOfPairs(nums1: number[], nums2: number[], k: number): number {
    const cnt1: Map<number, number> = new Map();
    for (const x of nums1) {
        if (x % k === 0) {
            cnt1.set((x / k) | 0, (cnt1.get((x / k) | 0) || 0) + 1);
        }
    }
    if (cnt1.size === 0) {
        return 0;
    }
    const cnt2: Map<number, number> = new Map();
    for (const x of nums2) {
        cnt2.set(x, (cnt2.get(x) || 0) + 1);
    }
    const mx = Math.max(...cnt1.keys());
    let ans = 0;
    for (const [x, v] of cnt2) {
        let s = 0;
        for (let y = x; y <= mx; y += x) {
            s += cnt1.get(y) || 0;
        }
        ans += s * v;
    }
    return ans;
}
