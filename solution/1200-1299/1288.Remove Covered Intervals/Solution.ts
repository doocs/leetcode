function removeCoveredIntervals(intervals: number[][]): number {
    intervals.sort((a, b) => (a[0] === b[0] ? b[1] - a[1] : a[0] - b[0]));
    let ans = 0;
    let pre = -Infinity;
    for (const [_, cur] of intervals) {
        if (cur > pre) {
            ++ans;
            pre = cur;
        }
    }
    return ans;
}
