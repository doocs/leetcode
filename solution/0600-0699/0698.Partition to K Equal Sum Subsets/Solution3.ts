function canPartitionKSubsets(nums: number[], k: number): boolean {
    let s = nums.reduce((a, b) => a + b);
    if (s % k !== 0) {
        return false;
    }
    s /= k;
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const f: boolean[] = Array(1 << n).fill(false);
    f[0] = true;
    const cur: number[] = Array(n).fill(0);
    for (let i = 0; i < 1 << n; ++i) {
        if (!f[i]) {
            continue;
        }
        for (let j = 0; j < n; ++j) {
            if (cur[i] + nums[j] > s) {
                break;
            }
            if (((i >> j) & 1) === 0) {
                f[i | (1 << j)] = true;
                cur[i | (1 << j)] = (cur[i] + nums[j]) % s;
            }
        }
    }
    return f[(1 << n) - 1];
}
