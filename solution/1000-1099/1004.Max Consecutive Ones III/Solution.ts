function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, cnt, j] = [0, 0, 0];
    for (let i = 0; i < n; ++i) {
        cnt += nums[i] ^ 1;
        while (cnt > k) {
            cnt -= nums[j++] ^ 1;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
