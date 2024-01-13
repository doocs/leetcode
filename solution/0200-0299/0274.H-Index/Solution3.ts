function hIndex(citations: number[]): number {
    let l = 0;
    let r = citations.length;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        let s = 0;
        for (const x of citations) {
            if (x >= mid) {
                ++s;
            }
        }
        if (s >= mid) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
