function countPairs(nums: number[], k: number): number {
    let ans = 0;
    for (let j = 1; j < nums.length; ++j) {
        for (let i = 0; i < j; ++i) {
            if (nums[i] === nums[j] && (i * j) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
