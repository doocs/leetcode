function threeSumSmaller(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; ++i) {
        let [j, k] = [i + 1, n - 1];
        while (j < k) {
            const x = nums[i] + nums[j] + nums[k];
            if (x < target) {
                ans += k - j;
                ++j;
            } else {
                --k;
            }
        }
    }
    return ans;
}
