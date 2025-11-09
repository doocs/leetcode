function longestSubarray(nums: number[]): number {
    const n = nums.length;
    const left: number[] = Array(n).fill(1);
    const right: number[] = Array(n).fill(1);

    for (let i = 1; i < n; i++) {
        if (nums[i] >= nums[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }

    for (let i = n - 2; i >= 0; i--) {
        if (nums[i] <= nums[i + 1]) {
            right[i] = right[i + 1] + 1;
        }
    }

    let ans = Math.max(...left);

    for (let i = 0; i < n; i++) {
        const a = i - 1 < 0 ? 0 : left[i - 1];
        const b = i + 1 >= n ? 0 : right[i + 1];
        if (i - 1 >= 0 && i + 1 < n && nums[i - 1] > nums[i + 1]) {
            ans = Math.max(ans, Math.max(a + 1, b + 1));
        } else {
            ans = Math.max(ans, a + b + 1);
        }
    }

    return ans;
}
