function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const l = 2 * k + 1;
    let sum = 0;
    let ans = new Array(n).fill(-1);
    for (let i = 0; i < n; i++) {
        sum += nums[i];
        let shiftIndex = i - l;
        if (shiftIndex > -1) {
            sum -= nums[shiftIndex];
        }
        if (i + 1 >= l) {
            ans[i - k] = Math.floor(sum / l);
        }
    }
    return ans;
}
