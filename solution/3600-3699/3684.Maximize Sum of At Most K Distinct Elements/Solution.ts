function maxKDistinct(nums: number[], k: number): number[] {
    nums.sort((a, b) => a - b);
    const ans: number[] = [];
    const n = nums.length;
    for (let i = n - 1; ~i; --i) {
        if (i + 1 < n && nums[i] === nums[i + 1]) {
            continue;
        }
        ans.push(nums[i]);
        if (--k === 0) {
            break;
        }
    }
    return ans;
}
