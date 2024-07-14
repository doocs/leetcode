function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const arr: number[][] = Array.from({ length: n }, (_, i) => [intervals[i][0], i]);
    arr.sort((a, b) => a[0] - b[0]);
    return intervals.map(([_, ed]) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr[mid][0] >= ed) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l < n ? arr[l][1] : -1;
    });
}
