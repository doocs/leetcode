function findRightInterval(intervals: number[][]): number[] {
    const n = intervals.length;
    const starts = Array.from({ length: n }).map(() => new Array<number>(2));
    for (let i = 0; i < n; i++) {
        starts[i][0] = intervals[i][0];
        starts[i][1] = i;
    }
    starts.sort((a, b) => a[0] - b[0]);

    return intervals.map(([_, target]) => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (starts[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left >= n) {
            return -1;
        }
        return starts[left][1];
    });
}
