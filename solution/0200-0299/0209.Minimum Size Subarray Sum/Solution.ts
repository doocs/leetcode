function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let res = Infinity;
    let sum = 0;
    let i = 0;
    let j = 0;
    while (j <= n) {
        if (sum < target) {
            sum += nums[j];
            j++;
        } else {
            res = Math.min(res, j - i);
            sum -= nums[i];
            i++;
        }
    }
    return res === Infinity ? 0 : res;
}
