function maxSum(nums: number[], k: number, mul: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;

    for (let i = n - 1; i >= n - k; i--) {
        const m = Math.max(1, mul);
        ans += nums[i] * m;
        mul--;
    }

    return ans;
}
