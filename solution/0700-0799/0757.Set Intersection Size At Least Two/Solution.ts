function intersectionSizeTwo(intervals: number[][]): number {
    intervals.sort((a, b) => (a[1] !== b[1] ? a[1] - b[1] : b[0] - a[0]));
    let s = -1;
    let e = -1;
    let ans = 0;
    for (const [a, b] of intervals) {
        if (a <= s) {
            continue;
        }
        if (a > e) {
            ans += 2;
            s = b - 1;
            e = b;
        } else {
            ans += 1;
            s = e;
            e = b;
        }
    }
    return ans;
}
