function minOperations(nums: number[], x: number): number {
    const n = nums.length;
    const target = nums.reduce((r, v) => r + v) - x;

    let l = 0;
    let r = 0;
    let sum = 0;
    let max = -1;
    while (r < n) {
        sum += nums[r++];
        while (sum > target && l < r) {
            sum -= nums[l++];
        }

        if (sum === target) {
            max = Math.max(max, r - l);
        }
    }

    if (max === -1) {
        return max;
    }
    return n - max;
}
