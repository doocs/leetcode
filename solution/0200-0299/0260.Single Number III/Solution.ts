function singleNumber(nums: number[]): number[] {
    const xs = nums.reduce((a, b) => a ^ b);
    const lb = xs & -xs;
    let a = 0;
    for (const x of nums) {
        if (x & lb) {
            a ^= x;
        }
    }
    const b = xs ^ a;
    return [a, b];
}
