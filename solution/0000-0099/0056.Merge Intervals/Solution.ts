function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    const ans: number[][] = [];
    let [st, ed] = intervals[0];
    for (const [s, e] of intervals.slice(1)) {
        if (ed < s) {
            ans.push([st, ed]);
            [st, ed] = [s, e];
        } else {
            ed = Math.max(ed, e);
        }
    }
    ans.push([st, ed]);
    return ans;
}
