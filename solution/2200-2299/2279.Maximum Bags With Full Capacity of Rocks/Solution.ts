function maximumBags(
    capacity: number[],
    rocks: number[],
    additionalRocks: number,
): number {
    const n = capacity.length;
    const diffs = capacity.map((c, i) => c - rocks[i]);
    diffs.sort((a, b) => a - b);
    let ans = 0;
    for (
        let i = 0;
        i < n && (diffs[i] === 0 || diffs[i] <= additionalRocks);
        i++
    ) {
        ans++;
        additionalRocks -= diffs[i];
    }
    return ans;
}
