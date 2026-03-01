function minDistinctFreqPair(nums: number[]): number[] {
    const inf = Number.MAX_SAFE_INTEGER;
    const cnt = new Map<number, number>();

    let x = inf;
    for (const v of nums) {
        cnt.set(v, (cnt.get(v) ?? 0) + 1);
        x = Math.min(x, v);
    }

    let minY = inf;
    for (const [y] of cnt) {
        if (y < minY && cnt.get(x)! !== cnt.get(y)!) {
            minY = y;
        }
    }

    return minY === inf ? [-1, -1] : [x, minY];
}
