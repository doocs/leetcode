function maxSubArray(nums: number[]): number {
    let [ans, f] = [-Infinity, -Infinity];
    for (const x of nums) {
        f = Math.max(f, 0) + x;
        ans = Math.max(ans, f);
    }
    return ans;
}
