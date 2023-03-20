function findSubarrays(nums: number[]): boolean {
    const vis: Set<number> = new Set<number>();
    for (let i = 1; i < nums.length; ++i) {
        const x = nums[i - 1] + nums[i];
        if (vis.has(x)) {
            return true;
        }
        vis.add(x);
    }
    return false;
}
