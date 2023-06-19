function rob(nums: number[]): number {
    let [f, g] = [0, nums[0]];
    for (let i = 1; i < nums.length; ++i) {
        [f, g] = [g, Math.max(f + nums[i], g)];
    }
    return g;
}
