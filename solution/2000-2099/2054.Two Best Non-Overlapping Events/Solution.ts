function maxTwoEvents(events: number[][]): number {
    events.sort((a, b) => a[0] - b[0]);
    const n = events.length;
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        f[i] = Math.max(f[i + 1], events[i][2]);
    }
    let ans = 0;
    for (const [_, end, v] of events) {
        let [left, right] = [0, n];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (events[mid][0] > end) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        const t = left < n ? f[left] : 0;
        ans = Math.max(ans, t + v);
    }
    return ans;
}
