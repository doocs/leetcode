function nextGreaterElements(nums: number[]): number[] {
    const stack: number[] = [],
        len = nums.length;
    const res: number[] = new Array(len).fill(-1);
    for (let i = 0; i < 2 * len - 1; i++) {
        const j = i % len;
        while (stack.length !== 0 && nums[stack[stack.length - 1]] < nums[j]) {
            res[stack[stack.length - 1]] = nums[j];
            stack.pop();
        }
        stack.push(j);
    }
    return res;
}
