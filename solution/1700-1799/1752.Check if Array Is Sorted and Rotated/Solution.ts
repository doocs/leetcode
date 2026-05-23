function check(nums: number[]): boolean {
    const n = nums.length;
    return nums.reduce((cnt, x, i) => cnt + (x > nums[(i + 1) % n] ? 1 : 0), 0) <= 1;
}
