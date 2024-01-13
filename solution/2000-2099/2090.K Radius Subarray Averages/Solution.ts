function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const ans: number[] = new Array(n).fill(-1);
    for (let i = 0; i < n; ++i) {
        if (i - k >= 0 && i + k < n) {
            ans[i] = Math.floor((s[i + k + 1] - s[i - k]) / ((k << 1) | 1));
        }
    }
    return ans;
}
