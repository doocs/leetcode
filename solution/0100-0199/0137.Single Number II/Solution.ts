function singleNumber(nums: number[]): number {
    let ans = 0;
    for (let i = 0; i < 32; i++) {
        const count = nums.reduce((r, v) => r + ((v >> i) & 1), 0);
        ans |= count % 3 << i;
    }
    return ans;
}
