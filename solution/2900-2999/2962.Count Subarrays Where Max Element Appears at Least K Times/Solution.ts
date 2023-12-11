function countSubarrays(nums: number[], k: number): number {
    const mx = Math.max(...nums);
    const n = nums.length;
    let [cnt, j] = [0, 0];
    let ans = 0;
    for (const x of nums) {
        for (; j < n && cnt < k; ++j) {
            cnt += nums[j] === mx ? 1 : 0;
        }
        if (cnt < k) {
            break;
        }
        ans += n - j + 1;
        cnt -= x === mx ? 1 : 0;
    }
    return ans;
}
