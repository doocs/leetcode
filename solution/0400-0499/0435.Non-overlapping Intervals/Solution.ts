function eraseOverlapIntervals(intervals: number[][]): number {
    let n = intervals.length;
    if (n == 0) return 0;
    intervals.sort((a, b) => a[1] - b[1]);
    let end = intervals[0][1], ans = 0;
    for (let i = 1; i < n; ++i) {
        let cur = intervals[i];
        if (end > cur[0]) {
            ans++;
        } else {
            end = cur[1];
        }
    }
    return ans;
};