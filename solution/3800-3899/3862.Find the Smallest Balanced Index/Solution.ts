function smallestBalancedIndex(nums: number[]): number {
    let s = 0;
    for (const x of nums) {
        s += x;
    }
    for (let i = nums.length - 1, p = 1; i >= 0; --i) {
        s -= nums[i];
        if (s === p) {
            return i;
        }
        p *= nums[i];
        if (p >= s) {
            break;
        }
    }
    return -1;
}
