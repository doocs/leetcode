function longestOnes(nums: number[], k: number): number {
    const n = nums.length;
    let l = 0;
    for (const num of nums) {
        if (num === 0) {
            k--;
        }
        if (k < 0 && nums[l++] === 0) {
            k++;
        }
    }
    return n - l;
}
