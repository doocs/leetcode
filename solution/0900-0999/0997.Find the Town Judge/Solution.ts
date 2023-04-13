function findJudge(n: number, trust: number[][]): number {
    const cnt1: number[] = new Array(n + 1).fill(0);
    const cnt2: number[] = new Array(n + 1).fill(0);
    for (const [a, b] of trust) {
        ++cnt1[a];
        ++cnt2[b];
    }
    for (let i = 1; i <= n; ++i) {
        if (cnt1[i] === 0 && cnt2[i] === n - 1) {
            return i;
        }
    }
    return -1;
}
