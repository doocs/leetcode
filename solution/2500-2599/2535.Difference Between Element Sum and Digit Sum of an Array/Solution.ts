function differenceOfSum(nums: number[]): number {
    return nums.reduce((r, v) => {
        r += v;
        while (v !== 0) {
            r -= v % 10;
            v = Math.floor(v / 10);
        }
        return r;
    }, 0);
}
