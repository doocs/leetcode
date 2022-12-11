function minOperations(nums: number[]): number {
    let ans = 0;
    let max = 0;
    for (const v of nums) {
        ans += Math.max(0, max + 1 - v);
        max = Math.max(max + 1, v);
    }
    return ans;
}
