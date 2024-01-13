function maximumCount(nums: number[]): number {
    const search = (target: number) => {
        let left = 0;
        let right = n;
        while (left < right) {
            const mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    };
    const n = nums.length;
    const i = search(0);
    const j = search(1);
    return Math.max(i, n - j);
}
