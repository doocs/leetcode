function search(nums: number[], target: number): boolean {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > nums[r]) {
            if (nums[l] <= target && target <= nums[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        } else if (nums[mid] < nums[r]) {
            if (nums[mid] < target && target <= nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        } else {
            --r;
        }
    }
    return nums[l] === target;
}
