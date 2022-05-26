function reductionOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let n = nums.length;
    let ans = 0,
        count = 0;
    for (let i = 1; i < n; i++) {
        if (nums[i] != nums[i - 1]) {
            count++;
        }
        ans += count;
    }
    return ans;
}
