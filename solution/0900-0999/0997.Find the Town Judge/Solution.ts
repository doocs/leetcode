function findJudge(n: number, trust: number[][]): number {
    let candidates = new Array(n).fill(0);
    for (let [a, b] of trust) {
        candidates[a - 1] = -1;
        if (candidates[b - 1] >= 0) {
            candidates[b - 1]++;
        }
    }

    for (let i = 0; i < n; i++) {
        if (candidates[i] == n - 1) {
            return i + 1;
        }
    }
    return -1;
}
