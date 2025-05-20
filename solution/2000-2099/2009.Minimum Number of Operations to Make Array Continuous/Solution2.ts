function minOperations(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let m = 1;
    for (let i = 1; i < n; i++) {
        if (nums[i] !== nums[i - 1]) {
            nums[m] = nums[i];
            m++;
        }
    }
    let ans = n;
    for (let i = 0, j = 0; i < m; i++) {
        while (j < m && nums[j] - nums[i] <= n - 1) {
            j++;
        }
        ans = Math.min(ans, n - (j - i));
    }
    return ans;
}
