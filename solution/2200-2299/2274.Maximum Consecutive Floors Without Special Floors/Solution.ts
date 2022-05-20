function maxConsecutive(
    bottom: number,
    top: number,
    special: number[],
): number {
    let nums = special.slice().sort((a, b) => a - b);
    nums.unshift(bottom - 1);
    nums.push(top + 1);
    let ans = 0;
    const n = nums.length;
    for (let i = 1; i < n; i++) {
        ans = Math.max(ans, nums[i] - nums[i - 1] - 1);
    }
    return ans;
}
