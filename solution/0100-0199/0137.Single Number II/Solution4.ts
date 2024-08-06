function singleNumber(nums: number[]): number {
    let [ans, acc] = [0, 0];

    for (const x of nums) {
        ans ^= x & ~acc;
        acc ^= x & ~ans;
    }

    return ans;
}
