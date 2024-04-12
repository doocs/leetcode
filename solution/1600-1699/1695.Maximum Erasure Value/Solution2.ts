function maximumUniqueSubarray(nums: number[]): number {
    const vis: Set<number> = new Set();
    let [ans, s, i] = [0, 0, 0];
    for (const x of nums) {
        while (vis.has(x)) {
            s -= nums[i];
            vis.delete(nums[i++]);
        }
        vis.add(x);
        s += x;
        ans = Math.max(ans, s);
    }
    return ans;
}
