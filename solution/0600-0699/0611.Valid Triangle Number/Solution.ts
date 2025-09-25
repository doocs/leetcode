function triangleNumber(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            const sum = nums[i] + nums[j];
            let k = _.sortedIndex(nums, sum, j + 1) - 1;
            if (k > j) {
                ans += k - j;
            }
        }
    }
    return ans;
}
