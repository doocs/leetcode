function minDeletions(nums: number[]): number {
    let ans: number = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1]) {
            ++ans;
        }
    }
    return ans;
}
