function numberOfSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    cnt[0] = 1;
    let ans = 0;
    let t = 0;
    for (const v of nums) {
        t += v & 1;
        if (t >= k) {
            ans += cnt[t - k];
        }
        cnt[t] += 1;
    }
    return ans;
}
