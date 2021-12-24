function increasingTriplet(nums: number[]): boolean {
    let n = nums.length;
    if (n < 3) return false;
    let min = nums[0],
        mid = Number.MAX_SAFE_INTEGER;
    for (let num of nums) {
        if (num <= min) {
            min = num;
        } else if (num <= mid) {
            mid = num;
        } else if (num > mid) {
            return true;
        }
    }
    return false;
}
