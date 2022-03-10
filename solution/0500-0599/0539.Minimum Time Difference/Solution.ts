function findMinDifference(timePoints: string[]): number {
    const mins = timePoints
        .map(item => Number(item.slice(0, 2)) * 60 + Number(item.slice(3, 5)))
        .sort((a, b) => a - b);
    const n = mins.length;
    let res = Infinity;
    for (let i = 0; i < n - 1; i++) {
        res = Math.min(res, mins[i + 1] - mins[i]);
    }

    const first = mins[0] + 24 * 60;
    const last = mins[n - 1];
    res = Math.min(res, first - last);

    return res;
}
