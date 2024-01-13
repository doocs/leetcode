function numIdenticalPairs(nums: number[]): number {
    const cnt = new Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let ans = 0;
    for (const v of cnt) {
        ans += v * (v - 1);
    }
    return ans >> 1;
}
