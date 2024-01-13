function storeWater(bucket: number[], vat: number[]): number {
    const mx = Math.max(...vat);
    if (mx === 0) {
        return 0;
    }
    const n = vat.length;
    let ans = 1 << 30;
    for (let x = 1; x <= mx; ++x) {
        let y = 0;
        for (let i = 0; i < n; ++i) {
            y += Math.max(0, Math.ceil(vat[i] / x) - bucket[i]);
        }
        ans = Math.min(ans, x + y);
    }
    return ans;
}
