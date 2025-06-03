function checkEqualPartitions(nums: number[], target: number): boolean {
    const n = nums.length;
    for (let i = 0; i < 1 << n; ++i) {
        let [x, y] = [1, 1];
        for (let j = 0; j < n; ++j) {
            if (((i >> j) & 1) === 1) {
                x *= nums[j];
            } else {
                y *= nums[j];
            }
            if (x > target || y > target) {
                break;
            }
        }
        if (x === target && y === target) {
            return true;
        }
    }
    return false;
}
