function firstUniqueFreq(nums: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    const freq = new Map<number, number>();
    for (const v of cnt.values()) {
        freq.set(v, (freq.get(v) ?? 0) + 1);
    }

    for (const x of nums) {
        if (freq.get(cnt.get(x)!) === 1) {
            return x;
        }
    }

    return -1;
}
