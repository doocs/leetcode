function minOperations(nums: number[], k: number): number {
    const n = nums.length;
    const isAdded = Array(k).fill(false);
    let count = 0;
    for (let i = n - 1; ; --i) {
        if (nums[i] > k || isAdded[nums[i] - 1]) {
            continue;
        }
        isAdded[nums[i] - 1] = true;
        ++count;
        if (count === k) {
            return n - i;
        }
    }
}
