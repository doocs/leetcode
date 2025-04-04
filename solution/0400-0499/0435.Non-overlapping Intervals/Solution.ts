function eraseOverlapIntervals(intervals: number[][]): number {
    intervals.sort((a, b) => a[1] - b[1]);
    let [ans, pre] = [intervals.length, -Infinity];
    for (const [l, r] of intervals) {
        if (pre <= l) {
            --ans;
            pre = r;
        }
    }
    return ans;
}
