function minSubsequence(nums: number[]): number[] {
    nums.sort((a, b) => b - a);
    const sum = nums.reduce((r, c) => r + c);
    const res: number[] = [];
    let t = 0;
    for (const num of nums) {
        t += num;
        res.push(num);
        if (t > sum - t) {
            break;
        }
    }
    return res;
}
