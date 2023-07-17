function findErrorNums(nums: number[]): number[] {
    const n = nums.length;
    let eor = 0;
    for (let i = 1; i <= n; ++i) {
        eor ^= i ^ nums[i - 1];
    }
    const lb = eor & -eor;
    let a = 0;
    for (let i = 1; i <= n; ++i) {
        if (i & lb) {
            a ^= i;
        }
        if (nums[i - 1] & lb) {
            a ^= nums[i - 1];
        }
    }
    const b = eor ^ a;
    for (const x of nums) {
        if (x === a) {
            return [a, b];
        }
    }
    return [b, a];
}
