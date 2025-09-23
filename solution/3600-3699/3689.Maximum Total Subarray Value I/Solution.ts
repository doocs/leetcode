function maxTotalValue(nums: number[], k: number): number {
    const mn = Math.min(...nums);
    const mx = Math.max(...nums);
    return k * (mx - mn);
}
