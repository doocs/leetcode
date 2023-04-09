function find132pattern(nums: number[]): boolean {
    let vk = -Infinity;
    const stk: number[] = [];
    for (let i = nums.length - 1; i >= 0; --i) {
        if (nums[i] < vk) {
            return true;
        }
        while (stk.length && stk[stk.length - 1] < nums[i]) {
            vk = stk.pop()!;
        }
        stk.push(nums[i]);
    }
    return false;
}
