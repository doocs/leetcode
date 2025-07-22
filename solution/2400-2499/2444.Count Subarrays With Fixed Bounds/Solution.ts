function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let ans = 0;
    let [j1, j2, k] = [-1, -1, -1];
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] < minK || nums[i] > maxK) k = i;
        if (nums[i] === minK) j1 = i;
        if (nums[i] === maxK) j2 = i;
        ans += Math.max(0, Math.min(j1, j2) - k);
    }
    return ans;
}
