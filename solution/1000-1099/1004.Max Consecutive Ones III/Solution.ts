function longestOnes(nums: number[], k: number): number {
    let [l, cnt] = [0, 0];
    for (const x of nums) {
        cnt += x ^ 1;
        if (cnt > k) {
            cnt -= nums[l++] ^ 1;
        }
    }
    return nums.length - l;
}
