function longestSubsequence(nums: number[]): number {
    let [xor, cnt0] = [0, 0];
    for (const x of nums) {
        xor ^= x;
        cnt0 += x === 0 ? 1 : 0;
    }
    const n = nums.length;
    if (xor) {
        return n;
    }
    if (cnt0 === n) {
        return 0;
    }
    return n - 1;
}
