function numberOfEmployeesWhoMetTarget(hours: number[], target: number): number {
    let ans = 0;
    for (const x of hours) {
        if (x >= target) {
            ++ans;
        }
    }
    return ans;
}
