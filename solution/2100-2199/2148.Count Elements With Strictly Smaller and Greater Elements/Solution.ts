function countElements(nums: number[]): number {
    const min = Math.min(...nums),
        max = Math.max(...nums);
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        let cur = nums[i];
        if (cur < max && cur > min) {
            ++ans;
        }
    }
    return ans;
}
