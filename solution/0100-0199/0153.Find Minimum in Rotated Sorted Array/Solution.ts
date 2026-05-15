function findMin(nums: number[]): number {
    let l = 0,
        r = nums.length - 1;
    while (l < r) {
        let mid = (l + r) >> 1;
        if (nums[mid] > nums[nums.length - 1]) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    return nums[l];
}
