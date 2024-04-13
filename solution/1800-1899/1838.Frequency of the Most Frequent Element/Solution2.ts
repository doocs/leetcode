function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = 1;
    let [s, j] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        s += (nums[i] - nums[i - 1]) * (i - j);
        while (s > k) {
            s -= nums[i] - nums[j++];
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
