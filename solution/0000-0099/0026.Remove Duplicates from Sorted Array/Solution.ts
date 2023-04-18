function removeDuplicates(nums: number[]): number {
    let k: number = 0;
    for (const x of nums) {
        if (k === 0 || x !== nums[k - 1]) {
            nums[k++] = x;
        }
    }
    return k;
}
