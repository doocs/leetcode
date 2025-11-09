function findMaximalUncoveredRanges(n: number, ranges: number[][]): number[][] {
    ranges.sort((a, b) => a[0] - b[0]);
    let last = -1;
    const ans: number[][] = [];
    for (const [l, r] of ranges) {
        if (last + 1 < l) {
            ans.push([last + 1, l - 1]);
        }
        last = Math.max(last, r);
    }
    if (last + 1 < n) {
        ans.push([last + 1, n - 1]);
    }
    return ans;
}
