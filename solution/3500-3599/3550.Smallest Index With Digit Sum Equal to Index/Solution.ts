function smallestIndex(nums: number[]): number {
    for (let i = 0; i < nums.length; ++i) {
        let s = 0;
        for (; nums[i] > 0; nums[i] = Math.floor(nums[i] / 10)) {
            s += nums[i] % 10;
        }
        if (s === i) {
            return i;
        }
    }
    return -1;
}
