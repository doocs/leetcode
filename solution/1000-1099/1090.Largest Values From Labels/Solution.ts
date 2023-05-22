function largestValsFromLabels(
    values: number[],
    labels: number[],
    numWanted: number,
    useLimit: number,
): number {
    const n = values.length;
    const pairs = new Array(n);
    for (let i = 0; i < n; ++i) {
        pairs[i] = [values[i], labels[i]];
    }
    pairs.sort((a, b) => b[0] - a[0]);
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, num = 0; i < n && num < numWanted; ++i) {
        const [v, l] = pairs[i];
        if ((cnt.get(l) || 0) < useLimit) {
            cnt.set(l, (cnt.get(l) || 0) + 1);
            ++num;
            ans += v;
        }
    }
    return ans;
}
