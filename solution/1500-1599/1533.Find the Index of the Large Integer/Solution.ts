function minSumSquareDiff(nums1: number[], nums2: number[], k1: number, k2: number): number {
    const n = nums1.length;
    const diff = new Array<number>(n);
    let maxDiff = 0;
    for (let i = 0; i < n; i++) {
        const d = Math.abs(nums1[i] - nums2[i]);
        diff[i] = d;
        if (d > maxDiff) maxDiff = d;
    }
    let k = k1 + k2;
    if (k === 0) {
        let ans = 0;
        for (let i = 0; i < n; i++) ans += diff[i] * diff[i];
        return ans;
    }
    const cnt = new Array<number>(maxDiff + 1).fill(0);
    for (let i = 0; i < n; i++) cnt[diff[i]]++;
    for (let v = maxDiff; v > 0 && k > 0; v--) {
        if (cnt[v] === 0) continue;
        const c = cnt[v];
        const need = c; 
        if (k >= need) {
            k -= need;
            cnt[v - 1] += c;
            cnt[v] = 0;
        } else {
            cnt[v] -= k;
            cnt[v - 1] += k;
            k = 0;
        }
    }
    let ans = 0;
    for (let v = 0; v <= maxDiff; v++) {
        if (cnt[v] > 0) ans += cnt[v] * v * v;
    }
    return ans;
}