function jobScheduling(startTime: number[], endTime: number[], profit: number[]): number {
    const n = profit.length;
    const jobs: [number, number, number][] = Array.from({ length: n }, (_, i) => [
        startTime[i],
        endTime[i],
        profit[i],
    ]);
    jobs.sort((a, b) => a[1] - b[1]);
    const dp: number[] = Array.from({ length: n + 1 }, () => 0);
    const search = (x: number, right: number): number => {
        let left = 0;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (jobs[mid][1] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    for (let i = 0; i < n; ++i) {
        const j = search(jobs[i][0], i);
        dp[i + 1] = Math.max(dp[i], dp[j] + jobs[i][2]);
    }
    return dp[n];
}
