function smallestRangeI(nums: number[], k: number): number {
    const mx = Math.max(...nums);
    const mi = Math.min(...nums);
    return Math.max(mx - mi - k * 2, 0);
}
