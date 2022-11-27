function check(nums: number[]): boolean {
    const n = nums.length;
    return (
        nums.reduce((r, v, i) => r + (v > nums[(i + 1) % n] ? 1 : 0), 0) <= 1
    );
}
