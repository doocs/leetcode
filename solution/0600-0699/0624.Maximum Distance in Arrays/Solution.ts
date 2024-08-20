function maxDistance(arrays: number[][]): number {
    const n = arrays.length;
    let res = 0;
    let [min, max] = [Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let i = 0; i < n; i++) {
        const a = arrays[i];
        res = Math.max(Math.max(a.at(-1)! - min, max - a[0]), res);
        min = Math.min(min, a[0]);
        max = Math.max(max, a.at(-1)!);
    }

    return res;
}
