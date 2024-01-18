function validSubarrays(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1)!;
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += right[i] - i;
    }
    return ans;
}
