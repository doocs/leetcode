function compareBitonicSums(nums: number[]): number {
    let l: number = nums[0];
    let r: number = nums.reduce((acc, curr) => acc + curr, 0);

    for (let i = 1; i < nums.length; i++) {
        if (nums[i - 1] > nums[i]) {
            break;
        }
        l += nums[i];
        r -= nums[i - 1];
    }

    if (l === r) {
        return -1;
    }
    return l > r ? 0 : 1;
}
