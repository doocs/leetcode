function canPartitionKSubsets(nums: number[], k: number): boolean {
    const dfs = (i: number): boolean => {
        if (i === nums.length) {
            return true;
        }
        for (let j = 0; j < k; j++) {
            if (j > 0 && cur[j] === cur[j - 1]) {
                continue;
            }
            cur[j] += nums[i];
            if (cur[j] <= s && dfs(i + 1)) {
                return true;
            }
            cur[j] -= nums[i];
        }
        return false;
    };

    let s = nums.reduce((a, b) => a + b, 0);
    const mod = s % k;
    if (mod !== 0) {
        return false;
    }
    s = Math.floor(s / k);
    const cur = Array(k).fill(0);
    nums.sort((a, b) => b - a);
    return dfs(0);
}
