function semiOrderedPermutation(nums: number[]): number {
    const n = nums.length;
    const i = nums.indexOf(1);
    const j = nums.indexOf(n);
    const k = i < j ? 1 : 2;
    return i + n - j - k;
}
