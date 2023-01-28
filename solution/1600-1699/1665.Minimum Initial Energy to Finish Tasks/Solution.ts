function minimumEffort(tasks: number[][]): number {
    tasks.sort((a, b) => a[0] - a[1] - (b[0] - b[1]));
    let ans = 0;
    let cur = 0;
    for (const [a, m] of tasks) {
        if (cur < m) {
            ans += m - cur;
            cur = m;
        }
        cur -= a;
    }
    return ans;
}
