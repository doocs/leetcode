function nextGreaterElements(nums: number[]): number[] {
    const n = nums.length;
    const stk: number[] = [];
    const ans: number[] = Array(n).fill(-1);
    for (let i = n * 2 - 1; ~i; --i) {
        const j = i % n;
        while (stk.length && stk.at(-1)! <= nums[j]) {
            stk.pop();
        }
        if (stk.length) {
            ans[j] = stk.at(-1)!;
        }
        stk.push(nums[j]);
    }
    return ans;
}
