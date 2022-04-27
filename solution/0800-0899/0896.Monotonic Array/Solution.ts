function isMonotonic(nums: number[]): boolean {
    const n = nums.length;
    let isOrder = false;
    let isDecs = false;
    for (let i = 1; i < n; i++) {
        const pre = nums[i - 1];
        const cur = nums[i];
        if (pre < cur) {
            isOrder = true;
        } else if (pre > cur) {
            isDecs = true;
        }
        if (isOrder && isDecs) {
            return false;
        }
    }
    return true;
}
