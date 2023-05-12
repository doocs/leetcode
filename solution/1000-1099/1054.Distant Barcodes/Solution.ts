function rearrangeBarcodes(barcodes: number[]): number[] {
    const mx = Math.max(...barcodes);
    const cnt = Array(mx + 1).fill(0);
    for (const x of barcodes) {
        ++cnt[x];
    }
    barcodes.sort((a, b) => (cnt[a] === cnt[b] ? a - b : cnt[b] - cnt[a]));
    const n = barcodes.length;
    const ans = Array(n);
    for (let k = 0, j = 0; k < 2; ++k) {
        for (let i = k; i < n; i += 2, ++j) {
            ans[i] = barcodes[j];
        }
    }
    return ans;
}
