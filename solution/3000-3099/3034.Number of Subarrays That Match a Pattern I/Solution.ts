function countMatchingSubarrays(nums: number[], pattern: number[]): number {
    const n: number = nums.length;
    const m: number = pattern.length;
    let count: number = 0;

    for (let i = 0; i <= n - m - 1; i++) {
        let flag: boolean = true;
        for (let j = 0; j < m; j++) {
            if ((pattern[j] === 1 && nums[i + j + 1] <= nums[i + j]) ||
                (pattern[j] === 0 && nums[i + j + 1] !== nums[i + j]) ||
                (pattern[j] === -1 && nums[i + j + 1] >= nums[i + j])) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count++;
        }
    }
    return count;
}
