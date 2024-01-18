function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const n = intervals.length;
    const res = [];
    let i = 0;
    while (i < n) {
        let [l, r] = intervals[i];
        i++;
        while (i < n && r >= intervals[i][0]) {
            r = Math.max(r, intervals[i][1]);
            i++;
        }
        res.push([l, r]);
    }
    return res;
}
