function isGood(nums: number[]): boolean {
    const n = nums.length - 1;
    const cnt: number[] = Array(201).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    if (cnt[n] !== 2) {
        return false;
    }
    for (let i = 1; i < n; ++i) {
        if (cnt[i] !== 1) {
            return false;
        }
    }
    return true;
}
