function countIntersectingIntervals(intervals: number[][]): number {
    const n = intervals.length;
    const starts = intervals.map(([s]) => s).sort((a, b) => a - b);
    const ends = intervals.map(([, e]) => e).sort((a, b) => a - b);
    let ans = (n * (n - 1)) / 2;
    let i = 0;
    for (const start of starts) {
        while (i < n && ends[i] < start) {
            i++;
        }
        ans -= i;
    }
    return ans;
}
