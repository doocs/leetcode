function findNumberOfLIS(nums: number[]): number {
    const n = nums.length;
    let [ans, mx] = [0, 0];
    const f: number[] = Array(n).fill(1);
    const cnt: number[] = Array(n).fill(1);
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[j] < nums[i]) {
                if (f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    cnt[i] = cnt[j];
                } else if (f[i] === f[j] + 1) {
                    cnt[i] += cnt[j];
                }
            }
        }
        if (mx < f[i]) {
            mx = f[i];
            ans = cnt[i];
        } else if (mx === f[i]) {
            ans += cnt[i];
        }
    }
    return ans;
}
