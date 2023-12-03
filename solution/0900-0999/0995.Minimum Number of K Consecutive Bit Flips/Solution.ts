function minKBitFlips(nums: number[], k: number): number {
    const n = nums.length;
    const d: number[] = Array(n + 1).fill(0);
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        s += d[i];
        if (s % 2 === nums[i] % 2) {
            if (i + k > n) {
                return -1;
            }
            d[i]++;
            d[i + k]--;
            s++;
            ans++;
        }
    }
    return ans;
}
