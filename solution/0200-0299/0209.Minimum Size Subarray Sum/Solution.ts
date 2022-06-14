function minSubArrayLen(target: number, nums: number[]): number {
    const n = nums.length;
    let res = n + 1;
    let sum = 0;
    let i = 0;
    for (let j = 0; j < n; j++) {
        sum += nums[j];
        while (sum >= target) {
            res = Math.min(res, j - i + 1);
            sum -= nums[i];
            i++;
        }
    }

    if (res === n + 1) {
        return 0;
    }
    return res;
}
