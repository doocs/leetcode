function maxNumOfMarkedIndices(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let i = 0;
    for (let j = (n + 1) >> 1; j < n; ++j) {
        if (nums[i] * 2 <= nums[j]) {
            ++i;
        }
    }
    return i * 2;
}
