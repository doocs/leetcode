function minBitwiseArray(nums: number[]): number[] {
    const ans: number[] = [];
    for (const x of nums) {
        if (x === 2) {
            ans.push(-1);
        } else {
            for (let i = 1; i < 32; ++i) {
                if (((x >> i) & 1) ^ 1) {
                    ans.push(x ^ (1 << (i - 1)));
                    break;
                }
            }
        }
    }
    return ans;
}
