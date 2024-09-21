function rearrangeArray(nums: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const m = (n + 1) >> 1;
    const ans: number[] = [];
    for (let i = 0; i < m; i++) {
        ans.push(nums[i]);
        if (i + m < n) {
            ans.push(nums[i + m]);
        }
    }
    return ans;
}
