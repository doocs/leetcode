function findDuplicate(nums: number[]): number {
    let left = 1,
        right = nums.length - 1;
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (let v of nums) {
            if (v <= mid) {
                ++cnt;
            }
        }
        if (cnt > mid) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
