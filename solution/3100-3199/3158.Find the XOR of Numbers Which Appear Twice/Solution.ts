function duplicateNumbersXOR(nums: number[]): number {
    const cnt: number[] = Array(51).fill(0);
    let ans: number = 0;
    for (const x of nums) {
        if (++cnt[x] === 2) {
            ans ^= x;
        }
    }
    return ans;
}
