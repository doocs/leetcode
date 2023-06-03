function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const cnt: number[] = Array(201).fill(0);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        if (++cnt[nums[i] + nums[n - i - 1]] === 1) {
            ++ans;
        }
    }
    return ans;
}
