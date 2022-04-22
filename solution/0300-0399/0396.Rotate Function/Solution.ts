function maxRotateFunction(nums: number[]): number {
    const n = nums.length;
    const sum = nums.reduce((r, v) => r + v);
    let res = nums.reduce((r, v, i) => r + v * i, 0);
    let pre = res;
    for (let i = 1; i < n; i++) {
        pre = pre - (sum - nums[i - 1]) + nums[i - 1] * (n - 1);
        res = Math.max(res, pre);
    }
    return res;
}
