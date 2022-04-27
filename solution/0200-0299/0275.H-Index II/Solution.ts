function hIndex(citations: number[]): number {
    const n = citations.length;
    let left = 0,
        right = n;
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (citations[n - mid] >= mid) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
