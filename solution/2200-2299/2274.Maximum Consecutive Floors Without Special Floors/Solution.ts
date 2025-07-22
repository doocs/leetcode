function maxConsecutive(bottom: number, top: number, special: number[]): number {
    special.sort((a, b) => a - b);
    const n = special.length;
    let ans = Math.max(special[0] - bottom, top - special[n - 1]);
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, special[i] - special[i - 1] - 1);
    }
    return ans;
}
