function hIndex(citations: number[]): number {
    const n: number = citations.length;
    const cnt: number[] = new Array(n + 1).fill(0);
    for (const x of citations) {
        ++cnt[Math.min(x, n)];
    }
    for (let h = n, s = 0; ; --h) {
        s += cnt[h];
        if (s >= h) {
            return h;
        }
    }
}
