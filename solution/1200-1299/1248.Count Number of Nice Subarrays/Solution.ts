function numberOfSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const cnt = Array(n + 1).fill(0);
    cnt[0] = 1;
    let [t, ans] = [0, 0];
    for (const v of nums) {
        t += v & 1;
        ans += cnt[t - k] ?? 0;
        cnt[t] += 1;
    }
    return ans;
}
