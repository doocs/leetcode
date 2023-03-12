function findMinimumTime(tasks: number[][]): number {
    tasks.sort((a, b) => a[1] - b[1]);
    const vis = new Array(2010).fill(0);
    let ans = 0;
    for (let [start, end, duration] of tasks) {
        for (let i = start; i <= end; ++i) {
            duration -= vis[i];
        }
        for (let i = end; i >= start && duration > 0; --i) {
            if (vis[i] === 0) {
                --duration;
                ans += vis[i] = 1;
            }
        }
    }
    return ans;
}
