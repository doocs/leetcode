function maximumAlternatingSubarraySum(nums: number[]): number {
    let [ans, f, g] = [-Infinity, -Infinity, -Infinity];
    for (const x of nums) {
        [f, g] = [Math.max(g, 0) + x, f - x];
        ans = Math.max(ans, f, g);
    }
    return ans;
}
