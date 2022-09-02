function countSubarrays(nums: number[]): number {
    let ans = 0;
    let i = 0;
    const n = nums.length;
    while (i < n) {
        let j = i + 1;
        while (j < n && nums[j] > nums[j - 1]) {
            ++j;
        }
        const cnt = j - i;
        ans += ((1 + cnt) * cnt) / 2;
        i = j;
    }
    return ans;
}
