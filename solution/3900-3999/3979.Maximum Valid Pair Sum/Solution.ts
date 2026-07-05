function maxValidPairSum(nums: number[], k: number): number {
    let [ans, x] = [0, 0];
    for (let j = k; j < nums.length; ++j) {
        const y = nums[j];
        x = Math.max(x, nums[j - k]);
        ans = Math.max(ans, x + y);
    }
    return ans;
}
