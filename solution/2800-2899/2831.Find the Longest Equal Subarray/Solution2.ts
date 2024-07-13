function longestEqualSubarray(nums: number[], k: number): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n + 1 }, () => []);
    for (let i = 0; i < n; ++i) {
        g[nums[i]].push(i);
    }
    let ans = 0;
    for (const ids of g) {
        let l = 0;
        for (let r = 0; r < ids.length; ++r) {
            while (ids[r] - ids[l] - (r - l) > k) {
                ++l;
            }
            ans = Math.max(ans, r - l + 1);
        }
    }
    return ans;
}
