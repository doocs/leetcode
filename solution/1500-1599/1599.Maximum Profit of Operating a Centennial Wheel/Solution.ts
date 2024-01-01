function minOperationsMaxProfit(
    customers: number[],
    boardingCost: number,
    runningCost: number,
): number {
    let ans: number = -1;
    let [mx, t, wait, i] = [0, 0, 0, 0];
    while (wait > 0 || i < customers.length) {
        wait += i < customers.length ? customers[i] : 0;
        let up: number = Math.min(4, wait);
        wait -= up;
        ++i;
        t += up * boardingCost - runningCost;

        if (t > mx) {
            mx = t;
            ans = i;
        }
    }

    return ans;
}
