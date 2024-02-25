function isPossibleToSplit(nums: number[]): boolean {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        if (++cnt[x] >= 3) {
            return false;
        }
    }
    return true;
}
