function minimumDeletions(nums: number[]): number {
    const n = nums.length;
    if (n == 1) return 1;
    let i = nums.indexOf(Math.min(...nums));
    let j = nums.indexOf(Math.max(...nums));
    let left = Math.min(i, j);
    let right = Math.max(i, j);
    return Math.min(left + 1 + n - right, right + 1, n - left);
}
