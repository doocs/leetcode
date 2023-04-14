function maxAbsoluteSum(nums: number[]): number {
    let f = 0;
    let g = 0;
    let ans = 0;
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        g = Math.min(g, 0) + x;
        ans = Math.max(ans, f, -g);
    }
    return ans;
}
