function singleNonDuplicate(nums: number[]): number {
    let [l, r] = [0, nums.length - 1];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] !== nums[mid ^ 1]) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return nums[l];
}
