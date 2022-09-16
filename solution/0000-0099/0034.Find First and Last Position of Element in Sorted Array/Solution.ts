function searchRange(nums: number[], target: number): number[] {
    function search(x) {
        let left = 0,
            right = nums.length;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    const l = search(target);
    const r = search(target + 1);
    return l == r ? [-1, -1] : [l, r - 1];
}
