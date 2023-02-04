function singleNumbers(nums: number[]): number[] {
    let xs = 0;
    for (const x of nums) {
        xs ^= x;
    }
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
