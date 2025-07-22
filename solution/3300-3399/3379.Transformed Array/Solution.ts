function constructTransformedArray(nums: number[]): number[] {
    const n = nums.length;
    const ans: number[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(nums[i] ? nums[(i + (nums[i] % n) + n) % n] : 0);
    }
    return ans;
}
