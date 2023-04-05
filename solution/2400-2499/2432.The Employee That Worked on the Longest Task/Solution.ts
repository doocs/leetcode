function hardestWorker(n: number, logs: number[][]): number {
    let [ans, mx, last] = [0, 0, 0];
    for (let [uid, t] of logs) {
        t -= last;
        if (mx < t || (mx == t && ans > uid)) {
            ans = uid;
            mx = t;
        }
        last += t;
    }
    return ans;
}
