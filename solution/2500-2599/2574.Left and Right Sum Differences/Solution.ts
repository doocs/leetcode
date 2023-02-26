function leftRigthDifference(nums: number[]): number[] {
    let left = 0,
        right = nums.reduce((a, b) => a + b);
    const ans: number[] = [];
    for (const x of nums) {
        right -= x;
        ans.push(Math.abs(left - right));
        left += x;
    }
    return ans;
}
