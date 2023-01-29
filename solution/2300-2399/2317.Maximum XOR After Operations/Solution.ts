function maximumXOR(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        ans |= x;
    }
    return ans;
}
