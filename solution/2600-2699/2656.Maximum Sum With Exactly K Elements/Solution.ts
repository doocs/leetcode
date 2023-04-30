function maximizeSum(nums: number[], k: number): number {
    const x = Math.max(...nums);
    return k * x + (k * (k - 1)) / 2;
}
