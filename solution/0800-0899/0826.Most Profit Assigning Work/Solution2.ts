function maxProfitAssignment(difficulty: number[], profit: number[], worker: number[]): number {
    const wMax = Math.max(...worker);
    const jobs = Array(wMax + 1).fill(0);
    const n = profit.length;

    for (let i = 0; i < n; i++) {
        const d = difficulty[i];
        if (d <= wMax) jobs[d] = Math.max(jobs[d], profit[i]);
    }

    for (let i = 1, x = 0; i <= wMax; i++) {
        jobs[i] = Math.max(jobs[i], jobs[i - 1]);
    }

    return worker.reduce((acc, w) => acc + jobs[w], 0);
}
