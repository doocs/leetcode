function maxAlternatingSum(nums: number[]): number {
    let [f, g] = [0, 0];
    for (const x of nums) {
        [f, g] = [Math.max(g - x, f), Math.max(f + x, g)];
    }
    return g;
}
