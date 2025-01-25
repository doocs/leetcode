/**
 * @param {number[]} jobs
 * @param {number[]} workers
 * @return {number}
 */
var minimumTime = function (jobs, workers) {
    jobs.sort((a, b) => a - b);
    workers.sort((a, b) => a - b);
    let ans = 0;
    const n = jobs.length;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, Math.ceil(jobs[i] / workers[i]));
    }
    return ans;
};
