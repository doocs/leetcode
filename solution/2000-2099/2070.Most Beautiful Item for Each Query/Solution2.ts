function maximumBeauty(items: number[][], queries: number[]): number[] {
    items.sort((a, b) => a[0] - b[0]);
    const n = items.length;
    for (let i = 1; i < n; ++i) {
        items[i][1] = Math.max(items[i][1], items[i - 1][1]);
    }
    const ans: number[] = [];
    for (const q of queries) {
        let l = 0,
            r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (items[mid][0] > q) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans.push(--l >= 0 ? items[l][1] : 0);
    }
    return ans;
}
