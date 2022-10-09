function hardestWorker(n: number, logs: number[][]): number {
    let [ans, max_num] = logs[0];
    for (let i = 1; i < logs.length; i++) {
        let duration = logs[i][1] - logs[i - 1][1];
        let id = logs[i][0];
        if (duration > max_num || (duration == max_num && id < ans)) {
            ans = id;
            max_num = duration;
        }
    }
    return ans;
}
