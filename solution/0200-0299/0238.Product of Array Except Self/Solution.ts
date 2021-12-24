function productExceptSelf(nums: number[]): number[] {
    let dpLeft = Array(nums.length).fill(1);
    let dpRight = Array(nums.length).fill(1);
    for (let i = 1; i < nums.length; i++) {
        dpLeft[i] = dpLeft[i - 1] * nums[i - 1];
    }
    for (let i = nums.length - 2; i >= 0; i--) {
        dpRight[i] = dpRight[i + 1] * nums[i + 1];
    }
    return dpLeft.map((x, i) => x * dpRight[i]);
}
