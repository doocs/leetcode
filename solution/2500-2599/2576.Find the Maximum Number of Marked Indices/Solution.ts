function maxNumOfMarkedIndices(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0, j = Math.floor((n + 1) / 2); j < n; ++i, ++j) {
        while (j < n && nums[i] * 2 > nums[j]) {
            ++j;
        }
        if (j < n) {
            ans += 2;
        }
    }
    return ans;
}
