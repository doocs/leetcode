function maxIncreasingGroups(usageLimits: number[]): number {
    usageLimits.sort((a, b) => a - b);
    let k = 0;
    let s = 0;
    for (const x of usageLimits) {
        s += x;
        if (s > k) {
            ++k;
            s -= k;
        }
    }
    return k;
}
