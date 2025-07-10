function maxFreeTime(eventTime: number, k: number, startTime: number[], endTime: number[]): number {
    const n = endTime.length;
    const nums: number[] = new Array(n + 1);
    nums[0] = startTime[0];
    for (let i = 1; i < n; i++) {
        nums[i] = startTime[i] - endTime[i - 1];
    }
    nums[n] = eventTime - endTime[n - 1];

    let [ans, s] = [0, 0];
    for (let i = 0; i <= n; i++) {
        s += nums[i];
        if (i >= k) {
            ans = Math.max(ans, s);
            s -= nums[i - k];
        }
    }
    return ans;
}
