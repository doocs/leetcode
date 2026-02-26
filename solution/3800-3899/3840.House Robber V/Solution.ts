function rob(nums: number[], colors: number[]): number {
    const n = nums.length;
    let f = 0;
    let g = nums[0];

    for (let i = 1; i < n; i++) {
        if (colors[i - 1] === colors[i]) {
            [f, g] = [Math.max(f, g), f + nums[i]];
        } else {
            [f, g] = [Math.max(f, g), Math.max(f, g) + nums[i]];
        }
    }

    return Math.max(f, g);
}
