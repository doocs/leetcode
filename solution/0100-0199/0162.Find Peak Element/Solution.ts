function findPeakElement(nums: number[]): number {
    let left = 0,
        right = nums.length - 1;
    while (left < right) {
        let mid: number = (left + right) >> 1;
        if (nums[mid] <= nums[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
