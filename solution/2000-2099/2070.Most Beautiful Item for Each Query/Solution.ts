function maximumBeauty(items: number[][], queries: number[]): number[] {
    const n = items.length;
    const m = queries.length;
    items.sort((a, b) => a[0] - b[0]);
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[i] - queries[j]);
    let [i, mx] = [0, 0];
    const ans: number[] = Array(m).fill(0);
    for (const j of idx) {
        while (i < n && items[i][0] <= queries[j]) {
            mx = Math.max(mx, items[i][1]);
            ++i;
        }
        ans[j] = mx;
    }
    return ans;
}
