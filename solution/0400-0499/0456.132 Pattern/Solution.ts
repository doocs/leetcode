function find132pattern(nums: number[]): boolean {
    const n = nums.length;
    if (n < 3) {
        return false;
    }
    let last = -Infinity;
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        const num = nums[i];
        if (num < last) {
            return true;
        }
        while (stack[stack.length - 1] < num) {
            last = Math.max(last, stack.pop());
        }
        stack.push(num);
    }
    return false;
}
