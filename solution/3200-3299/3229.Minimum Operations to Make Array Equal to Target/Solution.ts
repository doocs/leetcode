function minimumOperations(nums: number[], target: number[]): number {
    const n = nums.length;
    let f = Math.abs(target[0] - nums[0]);
    for (let i = 1; i < n; ++i) {
        const x = target[i] - nums[i];
        const y = target[i - 1] - nums[i - 1];
        if (x * y > 0) {
            const d = Math.abs(x) - Math.abs(y);
            if (d > 0) {
                f += d;
            }
        } else {
            f += Math.abs(x);
        }
    }
    return f;
}
