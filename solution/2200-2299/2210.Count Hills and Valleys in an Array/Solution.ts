function countHillValley(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let prev = nums[0];
    for (let i = 1; i < n - 1; i++) {
        const num = nums[i];
        const next = nums[i + 1];
        if (num == next) {
            continue;
        }
        if ((num > prev && num > next) || (num < prev && num < next)) {
            res += 1;
        }
        prev = num;
    }
    return res;
}
