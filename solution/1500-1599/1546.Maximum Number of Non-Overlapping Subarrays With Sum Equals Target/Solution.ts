function maxNonOverlapping(nums: number[], target: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        const vis: Set<number> = new Set();
        vis.add(0);
        for (; i < n; ++i) {
            s += nums[i];
            if (vis.has(s - target)) {
                ++ans;
                break;
            }
            vis.add(s);
        }
    }
    return ans;
}
