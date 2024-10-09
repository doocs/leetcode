function busyStudent(startTime: number[], endTime: number[], queryTime: number): number {
    const n = startTime.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
            ans++;
        }
    }
    return ans;
}
