function maxProfitAssignment(difficulty: number[], profit: number[], worker: number[]): number {
    const n = profit.length;
    worker.sort((a, b) => a - b);
    const jobs = Array.from({ length: n }, (_, i) => [difficulty[i], profit[i]]);
    jobs.sort((a, b) => a[0] - b[0]);
    let [ans, mx, i] = [0, 0, 0];
    for (const w of worker) {
        while (i < n && jobs[i][0] <= w) {
            mx = Math.max(mx, jobs[i++][1]);
        }
        ans += mx;
    }
    return ans;
}
