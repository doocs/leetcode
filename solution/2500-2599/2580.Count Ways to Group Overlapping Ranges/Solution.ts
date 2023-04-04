function countWays(ranges: number[][]): number {
    ranges.sort((a, b) => a[0] - b[0]);
    let mx = -1;
    let ans = 1;
    const mod = 10 ** 9 + 7;
    for (const [start, end] of ranges) {
        if (start > mx) {
            ans = (ans * 2) % mod;
        }
        mx = Math.max(mx, end);
    }
    return ans;
}
