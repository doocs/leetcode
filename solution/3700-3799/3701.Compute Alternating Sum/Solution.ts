function alternatingSum(nums: number[]): number {
    let ans: number = 0;
    for (let i = 0; i < nums.length; ++i) {
        ans += i % 2 === 0 ? nums[i] : -nums[i];
    }
    return ans;
}
