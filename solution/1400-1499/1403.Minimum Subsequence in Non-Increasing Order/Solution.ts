function minSubsequence(nums: number[]): number[] {
    nums.sort((a, b) => b - a);
    const s = nums.reduce((r, c) => r + c);
    let t = 0;
    for (let i = 0; ; ++i) {
        t += nums[i];
        if (t > s - t) {
            return nums.slice(0, i + 1);
        }
    }
}
