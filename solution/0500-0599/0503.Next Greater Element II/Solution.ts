function nextGreaterElements(nums: number[]): number[] {
    let stack: number[] = [], len = nums.length;
    let res: number[] = new Array(len).fill(-1);
    for (let i = 0; i < 2 * len - 1; i++) {
        while (stack.length !== 0 && nums[stack[stack.length - 1]] < nums[i % len]) {
            res[stack[stack.length - 1]] = nums[i % len];
            stack.pop();
        }
        stack.push(i % len);
    }
    return res;
};