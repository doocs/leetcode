function minPenalty(period: number, lights: number[], arrivalTime: number[]): number {
    const mx = Math.max(...lights);

    let ans = 0;

    for (const x of arrivalTime) {
        const r = x % period;

        if (r >= mx) {
            ans = Math.max(ans, period - r);
        }
    }

    return ans;
}
