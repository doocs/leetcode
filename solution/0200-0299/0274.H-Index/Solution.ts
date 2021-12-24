function hIndex(citations: number[]): number {
    let n = citations.length;
    let cnt = new Array(n + 1).fill(0);
    for (let c of citations) {
        if (c <= n) {
            ++cnt[c];
        } else {
            ++cnt[n];
        }
    }
    let sum = 0;
    for (let i = n; i > -1; --i) {
        sum += cnt[i];
        if (sum >= i) {
            return i;
        }
    }
    return 0;
}
