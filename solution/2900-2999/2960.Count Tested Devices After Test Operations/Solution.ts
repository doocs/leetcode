function countTestedDevices(batteryPercentages: number[]): number {
    let ans = 0;
    for (let x of batteryPercentages) {
        x -= ans;
        if (x > 0) {
            ++ans;
        }
    }
    return ans;
}
