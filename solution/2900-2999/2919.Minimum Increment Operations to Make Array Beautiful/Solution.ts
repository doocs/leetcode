function minIncrementOperations(nums: number[], k: number): number {
    let [f, g, h] = [0, 0, 0];
    for (const x of nums) {
        [f, g, h] = [g, h, Math.min(f, g, h) + Math.max(k - x, 0)];
    }
    return Math.min(f, g, h);
}
