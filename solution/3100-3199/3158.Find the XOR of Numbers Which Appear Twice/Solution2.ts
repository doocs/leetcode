function duplicateNumbersXOR(nums: number[]): number {
    let ans = 0;
    let mask = 0n;
    for (const x of nums) {
        if ((mask >> BigInt(x)) & 1n) {
            ans ^= x;
        } else {
            mask |= 1n << BigInt(x);
        }
    }
    return ans;
}
