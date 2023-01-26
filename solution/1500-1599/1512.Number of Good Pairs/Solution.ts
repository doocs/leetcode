function numIdenticalPairs(nums: number[]): number {
    const cnt = new Array(101).fill(0);
    let ans = 0;
    for (const x of nums) {
        ans += cnt[x]++;
    }
    return ans;
}
