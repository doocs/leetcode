function countTestedDevices(batteryPercentages: number[]): number {
    let ans = 0;
    for (const x of batteryPercentages) {
        ans += x > ans ? 1 : 0;
    }
    return ans;
}
