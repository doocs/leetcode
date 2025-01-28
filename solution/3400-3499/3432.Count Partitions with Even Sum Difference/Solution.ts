function countPartitions(nums: number[]): number {
    let l = 0;
    let r = nums.reduce((a, b) => a + b, 0);
    let ans = 0;
    for (const x of nums.slice(0, -1)) {
        l += x;
        r -= x;
        ans += (l - r) % 2 === 0 ? 1 : 0;
    }
    return ans;
}
