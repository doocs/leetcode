function maximizeGreatness(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let i = 0;
    for (const x of nums) {
        if (x > nums[i]) {
            i += 1;
        }
    }
    return i;
}
