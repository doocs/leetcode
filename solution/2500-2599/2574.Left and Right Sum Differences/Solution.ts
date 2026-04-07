function leftRightDifference(nums: number[]): number[] {
    let [l, r] = [0, nums.reduce((a, b) => a + b, 0)];
    const ans: number[] = [];
    for (const x of nums) {
        r -= x;
        ans.push(Math.abs(l - r));
        l += x;
    }
    return ans;
}
