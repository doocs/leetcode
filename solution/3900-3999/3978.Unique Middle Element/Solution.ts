function isMiddleElementUnique(nums: number[]): boolean {
    let cnt: number = 0;
    for (const x of nums) {
        if (x === nums[nums.length >> 1]) {
            ++cnt;
        }
    }
    return cnt === 1;
}
