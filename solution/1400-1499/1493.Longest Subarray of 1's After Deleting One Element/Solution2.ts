function longestSubarray(nums: number[]): number {
    let [ans, cnt, j] = [0, 0, 0];
    for (let i = 0; i < nums.length; ++i) {
        cnt += nums[i] ^ 1;
        while (cnt > 1) {
            cnt -= nums[j++] ^ 1;
        }
        ans = Math.max(ans, i - j);
    }
    return ans;
}
