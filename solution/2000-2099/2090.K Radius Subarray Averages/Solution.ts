function getAverages(nums: number[], k: number): number[] {
    k = (k << 1) | 1;
    const n = nums.length;
    const ans: number[] = Array(n).fill(-1);
    if (k > n) {
        return ans;
    }
    let s = nums.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let j = k >> 1;
    ans[j] = Math.floor(s / k);
    for (let i = k; i < n; ++i) {
        s += nums[i] - nums[i - k];
        ans[++j] = Math.floor(s / k);
    }
    return ans;
}
