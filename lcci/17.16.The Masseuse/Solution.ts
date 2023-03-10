function massage(nums: number[]): number {
    let f = 0,
        g = 0;
    for (const x of nums) {
        const ff = g + x;
        const gg = Math.max(f, g);
        f = ff;
        g = gg;
    }
    return Math.max(f, g);
}
