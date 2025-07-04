function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const f = (i: number): number => {
        if (i === 0) {
            return startTime[0];
        }
        if (i === n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    };
    let ans = 0;
    let s = 0;
    for (let i = 0; i <= n; i++) {
        s += f(i);
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= f(i - k);
        }
    }
    return ans;
}
