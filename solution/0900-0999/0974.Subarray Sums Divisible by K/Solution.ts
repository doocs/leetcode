function subarraysDivByK(nums: number[], k: number): number {
    const cnt: { [key: number]: number } = { 0: 1 };
    let s = 0;
    let ans = 0;
    for (const x of nums) {
        s = (((s + x) % k) + k) % k;
        ans += cnt[s] || 0;
        cnt[s] = (cnt[s] || 0) + 1;
    }
    return ans;
}
