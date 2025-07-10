function maxFreeTime(eventTime: number, startTime: number[], endTime: number[]): number {
    const n = startTime.length;
    const pre: number[] = Array(n).fill(0);
    const suf: number[] = Array(n).fill(0);

    pre[0] = startTime[0];
    suf[n - 1] = eventTime - endTime[n - 1];

    for (let i = 1; i < n; i++) {
        pre[i] = Math.max(pre[i - 1], startTime[i] - endTime[i - 1]);
    }

    for (let i = n - 2; i >= 0; i--) {
        suf[i] = Math.max(suf[i + 1], startTime[i + 1] - endTime[i]);
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        const l = i === 0 ? 0 : endTime[i - 1];
        const r = i === n - 1 ? eventTime : startTime[i + 1];
        const w = endTime[i] - startTime[i];

        ans = Math.max(ans, r - l - w);

        if (i > 0 && pre[i - 1] >= w) {
            ans = Math.max(ans, r - l);
        } else if (i + 1 < n && suf[i + 1] >= w) {
            ans = Math.max(ans, r - l);
        }
    }

    return ans;
}
