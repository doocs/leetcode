function maximumCount(nums: number[]): number {
    const search = (x: number): number => {
        let [left, right] = [0, nums.length];
        while (left < right) {
            const mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    };
    const i = search(1);
    const j = search(0);
    const [a, b] = [nums.length - i, j];
    return Math.max(a, b);
}
