function minRemoval(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        let j = n;
        if (nums[i] * k <= nums[n - 1]) {
            const target = nums[i] * k + 1;
            j = _.sortedIndexBy(nums, target, x => x);
        }
        cnt = Math.max(cnt, j - i);
    }
    return n - cnt;
}
