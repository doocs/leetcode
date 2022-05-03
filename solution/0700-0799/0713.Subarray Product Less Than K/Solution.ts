function numSubarrayProductLessThanK(nums: number[], k: number): number {
    if (k <= 1) {
        return 0;
    }

    const n = nums.length;
    let res = 0;
    for (let i = 0; i < n; i++) {
        let product = 1;
        for (let j = i; j < n; j++) {
            product *= nums[j];
            if (product >= k) {
                break;
            }
            res++;
        }
    }
    return res;
}
