function minimumDeletions(nums: number[]): number {
    const n = nums.length;
    if (n == 1) return 1;
    let i = nums.indexOf(Math.min(...nums));
    let j = nums.indexOf(Math.max(...nums));
    let left = Math.min(i, j);
    let right = Math.max(i, j);
    // 左右 left + 1 + n - right
    // 两个都是左边 left + 1 + right - left = right + 1
    // 都是右边 n - right + right - left = n - left
    return Math.min(left + 1 + n - right, right + 1, n - left);
}
