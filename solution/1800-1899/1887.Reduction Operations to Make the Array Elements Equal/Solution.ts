function reductionOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let [ans, cnt] = [0, 0];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] !== nums[i - 1]) {
            ++cnt;
        }
        ans += cnt;
    }
    return ans;
}
