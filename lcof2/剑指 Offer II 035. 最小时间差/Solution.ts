function findMinDifference(timePoints: string[]): number {
    if (timePoints.length > 24 * 60) {
        return 0;
    }
    const mins: number[] = timePoints.map(timePoint => {
        const [hour, minute] = timePoint.split(':').map(num => parseInt(num));
        return hour * 60 + minute;
    });
    mins.sort((a, b) => a - b);
    mins.push(mins[0] + 24 * 60);
    let ans = 1 << 30;
    for (let i = 1; i < mins.length; ++i) {
        ans = Math.min(ans, mins[i] - mins[i - 1]);
    }
    return ans;
}
