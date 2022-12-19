function numIdenticalPairs(nums: number[]): number {
    const count = new Array(101).fill(0);
    let ans = 0;
    for (const num of nums) {
        ans += count[num]++;
    }
    return ans;
}
