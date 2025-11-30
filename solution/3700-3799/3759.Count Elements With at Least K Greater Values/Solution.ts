function countElements(nums: number[], k: number): number {
    const n = nums.length;
    if (k === 0) {
        return n;
    }
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < n - k; ++i) {
        if (nums[n - k] > nums[i]) {
            ++ans;
        }
    }
    return ans;
}
