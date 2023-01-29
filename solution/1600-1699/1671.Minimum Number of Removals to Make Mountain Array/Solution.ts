function minimumMountainRemovals(nums: number[]): number {
    const n = nums.length;
    const left = new Array(n).fill(1);
    const right = new Array(n).fill(1);
    for (let i = 1; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (nums[i] > nums[j]) {
                left[i] = Math.max(left[i], left[j] + 1);
            }
        }
    }
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (nums[i] > nums[j]) {
                right[i] = Math.max(right[i], right[j] + 1);
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (left[i] > 1 && right[i] > 1) {
            ans = Math.max(ans, left[i] + right[i] - 1);
        }
    }
    return n - ans;
}
