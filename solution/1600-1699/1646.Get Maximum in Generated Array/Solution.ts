function getMaximumGenerated(n: number): number {
    if (n === 0) {
        return 0;
    }
    const nums: number[] = new Array(n + 1).fill(0);
    nums[1] = 1;
    for (let i = 2; i < n + 1; ++i) {
        nums[i] =
            i % 2 === 0 ? nums[i >> 1] : nums[i >> 1] + nums[(i >> 1) + 1];
    }
    return Math.max(...nums);
}
