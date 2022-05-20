function largestCombination(candidates: number[]): number {
    const n = 24;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let count = 0;
        for (let num of candidates) {
            if ((num >> i) & 1) count++;
        }
        ans = Math.max(ans, count);
    }
    return ans;
}
