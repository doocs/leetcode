function minOperations(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (nums[i] === 0) {
            if (i + 2 >= n) {
                return -1;
            }
            nums[i + 1] ^= 1;
            nums[i + 2] ^= 1;
            ++ans;
        }
    }
    return ans;
}
