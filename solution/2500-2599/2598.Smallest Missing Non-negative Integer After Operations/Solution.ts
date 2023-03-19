function findSmallestInteger(nums: number[], value: number): number {
    const cnt: number[] = new Array(value).fill(0);
    for (const x of nums) {
        ++cnt[((x % value) + value) % value];
    }
    for (let i = 0; ; ++i) {
        if (cnt[i % value]-- === 0) {
            return i;
        }
    }
}
