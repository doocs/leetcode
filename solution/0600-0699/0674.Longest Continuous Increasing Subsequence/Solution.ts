function findLengthOfLCIS(nums: number[]): number {
    let [ans, cnt] = [1, 1];
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i - 1] < nums[i]) {
            ans = Math.max(ans, ++cnt);
        } else {
            cnt = 1;
        }
    }
    return ans;
}
