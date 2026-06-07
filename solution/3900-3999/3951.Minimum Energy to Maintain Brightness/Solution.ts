function minEnergy(n: number, brightness: number, intervals: number[][]): number {
    intervals.sort((a, b) => a[0] - b[0]);
    const merged: number[][] = [intervals[0]];
    for (let i = 1; i < intervals.length; i++) {
        const x = intervals[i];
        if (merged[merged.length - 1][1] < x[0]) {
            merged.push(x);
        } else {
            merged[merged.length - 1][1] = Math.max(merged[merged.length - 1][1], x[1]);
        }
    }
    let ans = 0;
    for (const [start, end] of merged) {
        const m = end - start + 1;
        ans += Math.ceil(brightness / 3) * m;
    }
    return ans;
}
