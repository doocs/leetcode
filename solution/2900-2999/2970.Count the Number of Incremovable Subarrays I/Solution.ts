function incremovableSubarrayCount(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    while (i + 1 < n && nums[i] < nums[i + 1]) {
        i++;
    }
    if (i === n - 1) {
        return (n * (n + 1)) / 2;
    }
    let ans = i + 2;
    for (let j = n - 1; j; --j) {
        while (i >= 0 && nums[i] >= nums[j]) {
            --i;
        }
        ans += i + 2;
        if (nums[j - 1] >= nums[j]) {
            break;
        }
    }
    return ans;
}
