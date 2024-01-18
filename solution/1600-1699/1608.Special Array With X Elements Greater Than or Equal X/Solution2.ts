function specialArray(nums: number[]): number {
    const n = nums.length;
    let left = 0;
    let right = n + 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        const count = nums.reduce((r, v) => r + (v >= mid ? 1 : 0), 0);

        if (count === mid) {
            return mid;
        }

        if (count > mid) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return -1;
}
