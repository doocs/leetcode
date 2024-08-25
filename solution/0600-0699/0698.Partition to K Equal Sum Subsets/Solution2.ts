function canPartitionKSubsets(nums: number[], k: number): boolean {
    let s = nums.reduce((a, b) => a + b, 0);
    if (s % k !== 0) {
        return false;
    }
    s = Math.floor(s / k);
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const mask = (1 << n) - 1;
    const f = Array(1 << n).fill(0);

    const dfs = (state: number, t: number): boolean => {
        if (state === mask) {
            return true;
        }
        if (f[state] !== 0) {
            return f[state] === 1;
        }
        for (let i = 0; i < n; ++i) {
            if ((state >> i) & 1) {
                continue;
            }
            if (t + nums[i] > s) {
                break;
            }
            if (dfs(state | (1 << i), (t + nums[i]) % s)) {
                f[state] = 1;
                return true;
            }
        }
        f[state] = -1;
        return false;
    };

    return dfs(0, 0);
}
