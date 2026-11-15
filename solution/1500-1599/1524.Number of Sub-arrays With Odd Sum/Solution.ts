function numOfSubarrays(arr: number[]): number {
    const cnt: number[] = [1, 0];
    let ans = 0;
    let s = 0;
    const mod = 1e9 + 7;
    for (const x of arr) {
        s = (s + x) % 2;
        ans = (ans + cnt[s ^ 1]) % mod;
        cnt[s]++;
    }
    return ans;
}
