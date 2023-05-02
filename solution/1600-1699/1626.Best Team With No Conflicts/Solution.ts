function bestTeamScore(scores: number[], ages: number[]): number {
    const arr = ages.map((age, i) => [age, scores[i]]);
    arr.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
    const n = arr.length;
    const f = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (arr[i][1] >= arr[j][1]) {
                f[i] = Math.max(f[i], f[j]);
            }
        }
        f[i] += arr[i][1];
    }
    return Math.max(...f);
}
