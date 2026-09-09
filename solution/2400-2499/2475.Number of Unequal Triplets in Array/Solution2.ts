function unequalTriplets(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    const search = (x: number, left: number, right: number): number => {
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
    for (let j = 1; j < n - 1; ++j) {
        const i = search(nums[j], 0, j) - 1;
        const k = search(nums[j] + 1, j + 1, n);
        if (i >= 0 && k < n) {
            ans += (i + 1) * (n - k);
        }
    }
    return ans;
}
